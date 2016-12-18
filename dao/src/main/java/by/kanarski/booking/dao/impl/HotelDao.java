package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dto.SearchOrder;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class HotelDao extends ExtendedBaseDao<Hotel> implements IHotelDao {

    @Autowired
    public HotelDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

//    @Override
//    public List<Hotel> getListByOrder(Order order, int page, int perPage) throws DaoException {
//        List<Hotel> hotelList = null;
//        try {
//            Criteria criteria = getCritetiaByOrder(order);
//            hotelList = getListByCriteria(criteria, page, perPage);
//        } catch (HibernateException e) {
//            throw new DaoException(e);
//        }
//        return hotelList;
//    }

    @Override
    public List<Hotel> getListByOrder(SearchOrder searchOrder, int page, int perPage) throws DaoException {
        List<Hotel> hotelList = null;
        try {
            List<Long> hotelIdList = getHotelIdList(searchOrder, page, perPage);
            Criteria criteria = getSession().createCriteria(Hotel.class)
                    .add(Restrictions.in(SearchParameter.HOTELID, hotelIdList));
            hotelList = criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return hotelList;
    }

    @Override
    public Long getHotelsCount(SearchOrder searchOrder) throws DaoException {
        Long hotelsCount = null;
        try {
            Criteria criteria = getCritetiaByOrder(searchOrder);
            // TODO: 12.12.2016 Подумать над универсальнойстью

            criteria.setProjection(Projections.countDistinct(SearchParameter.HOTELID));
            hotelsCount = getCountByCriteria(criteria);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return hotelsCount;
    }

    private Criteria getCritetiaByOrder(SearchOrder searchOrder) throws HibernateException {
        Hotel hotel = searchOrder.getHotel();
        RoomType roomType = searchOrder.getRoomType();
        Long hotelId = hotel.getHotelId();
        String country = hotel.getLocation().getCountry();
        String city = hotel.getLocation().getCity();
        Criteria criteria = getSession().createCriteria(Hotel.class);
        criteria
                .createAlias(AliasName.LOCATION, AliasValue.LOCATION)
                .createAlias(AliasName.ROOMSET, AliasValue.ROOMSET)
                .createAlias(AliasName.ROOMSET_ROOMTYPE, AliasValue.ROOMTYPE, JoinType.LEFT_OUTER_JOIN)
                .createAlias(AliasName.ROOMSET_BILLSET, AliasValue.BILLSET, JoinType.LEFT_OUTER_JOIN)
                .createAlias(AliasName.ROOMTYPE_FACILITYSET, AliasValue.FACILITYSET, JoinType.LEFT_OUTER_JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
        Integer totalPersons = searchOrder.getTotalPersons();
        Integer totalRooms = searchOrder.getTotalRooms();
        Integer maxPersons = Math.round(totalPersons / totalRooms);
        if (roomType != null) {
            maxPersons = (roomType.getMaxPersons() != null) ? roomType.getMaxPersons() : maxPersons;
            String roomTypeName = roomType.getRoomTypeName();
            Double pricePerNight = roomType.getPricePerNight();
            if (StringUtils.isNotBlank(roomTypeName)) {
                criteria.add(Restrictions.eq(SearchParameter.ROOMTYPE_ROOMTYPENAME, roomTypeName));
            }
            if (pricePerNight != null) {
                criteria.add(Restrictions.lt(SearchParameter.ROOMTYPE_PRICEPERNIGHT, pricePerNight));
            }
            Set<Facility> facilitySet = roomType.getFacilitySet();
            if (CollectionUtils.isNotEmpty(facilitySet)) {
                Set<String> facilityStringSet = new HashSet<>();
                for (Facility facility : facilitySet) {
                    facilityStringSet.add(facility.getFacilityName());
                }
                criteria.add(Restrictions.in(SearchParameter.FACILITYSET_FACILITYNAME, facilityStringSet));
            }
        }
        maxPersons = (maxPersons <= 0) ? 1 : maxPersons;
        criteria.add(Restrictions.ge(SearchParameter.ROOMTYPE_MAXPERSONS, maxPersons));
        Long checkInDate = searchOrder.getCheckInDate();
        Long checkOutDate = searchOrder.getCheckOutDate();
        Conjunction condition1 = Restrictions.conjunction();
        condition1
                .add(Restrictions.gt(SearchParameter.BILLSET_CHECKINDATE, checkInDate))
                .add(Restrictions.lt(SearchParameter.BILLSET_CHECKOUTDATE, checkOutDate));
        Conjunction condition2 = Restrictions.conjunction();
        condition2
                .add(Restrictions.lt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                .add(Restrictions.gt(SearchParameter.BILLSET_CHECKINDATE, checkOutDate));
        Conjunction condition3 = Restrictions.conjunction();
        condition3
                .add(condition1)
                .add(condition2);
        Conjunction condition4 = Restrictions.conjunction();
        condition4
                .add(Restrictions.lt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                .add(Restrictions.not(Restrictions.between(SearchParameter.BILLSET_CHECKINDATE, checkInDate, checkOutDate)));
        Criterion nullDateCriterion = Restrictions.isNull(SearchParameter.BILLSET_CHECKINDATE);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction
                .add(nullDateCriterion)
                .add(condition3)
                .add(condition4);
        criteria.add(disjunction);
        Order sortPriceOrder = null;
        if (searchOrder.getSortPriceAsc()) {
            sortPriceOrder = Order.asc(SearchParameter.ROOMTYPE_PRICEPERNIGHT);
        } else {
            sortPriceOrder = Order.desc(SearchParameter.ROOMTYPE_PRICEPERNIGHT);
        }
        criteria.addOrder(sortPriceOrder);
        return criteria;
    }

    private List<Long> getHotelIdList(SearchOrder searchOrder, int page, int perPage) {
        Criteria criteria = getCritetiaByOrder(searchOrder)
                .setProjection(Projections.distinct(Projections.property(SearchParameter.HOTELID)))
                .setFirstResult(page)
                .setMaxResults(perPage);
        List<Long> resultList;
        resultList = criteria.list();
        return resultList;
    }
}
