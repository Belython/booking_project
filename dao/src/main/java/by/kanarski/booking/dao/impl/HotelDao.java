package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dto.Order;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDao extends ExtendedBaseDao<Hotel> implements IHotelDao {

    @Autowired
    public HotelDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Hotel> getListByOrder(Order order, int page, int perPage) throws DaoException {
        List<Hotel> hotelList = null;
        Hotel hotel = order.getHotel();
        Long hotelId = hotel.getHotelId();
        String country = hotel.getLocation().getCountry();
        String city = hotel.getLocation().getCity();
        Criteria criteria = getSession().createCriteria(Hotel.class);
        criteria
                .createAlias(AliasName.LOCATION, AliasValue.LOCATION)
                .createAlias(AliasName.ROOMSET, AliasValue.ROOMSET)
                .createAlias(AliasName.ROOMSET_ROOMTYPE, AliasValue.ROOMTYPE, JoinType.LEFT_OUTER_JOIN)
                .createAlias(AliasName.ROOMSET_BILLSET, AliasValue.BILLSET, JoinType.LEFT_OUTER_JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        try {
            if (hotelId != null) {
                criteria.add(Restrictions.eq(SearchParameter.HOTELID, hotelId));
            } else {
                if (StringUtils.isNotBlank(country)) {
                    criteria.add(Restrictions.eq(SearchParameter.LOCATION_COUNTRY, country));
                    if (StringUtils.isNotBlank(city)) {
                        criteria.add(Restrictions.eq(SearchParameter.LOCATION_CITY, city));
                    }
                }
            }
            Integer totalPersons = order.getTotalPersons();
            Integer totalRooms = order.getTotalRooms();
            Integer maxPersons = Math.round(totalPersons / totalRooms);
            maxPersons = (maxPersons <= 0) ? 1 : maxPersons;
            criteria.add(Restrictions.ge(SearchParameter.ROOMTYPE_MAXPERSONS, maxPersons));
            Long checkInDate = order.getCheckInDate();
            Long checkOutDate = order.getCheckOutDate();
            Conjunction condition1 = Restrictions.conjunction();
            condition1
                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkInDate))
                    .add(Restrictions.ge(SearchParameter.BILLSET_CHECKOUTDATE, checkOutDate));
            Conjunction condition2 = Restrictions.conjunction();
            condition2
                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkOutDate));
            Conjunction condition3 = Restrictions.conjunction();
            condition3
                    .add(condition1)
                    .add(condition2);
            Conjunction condition4 = Restrictions.conjunction();
            condition4
                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                    .add(Restrictions.between(SearchParameter.BILLSET_CHECKINDATE, checkInDate, checkOutDate));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction
                    .add(condition3)
                    .add(condition4);
            hotelList = getListByCriteria(criteria, page, perPage);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return hotelList;
    }

}
