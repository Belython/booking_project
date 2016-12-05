package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IRoomDao;
import by.kanarski.booking.entities.Room;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDao extends ExtendedBaseDao<Room> implements IRoomDao {

    @Autowired
    public RoomDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

//    @Override
//    public List<Room> getAvailableRooms(OrderDto orderDto, int page, int perPage) throws DaoException {
//        List<RoomDto> roomDtoList = null;
//        HotelDto hotelDto = orderDto.getHotel();
//        Long hotelId = hotelDto.getHotelId();
//        String localizedCountry = hotelDto.getLocation().getCountry();
//        String localizedCity = hotelDto.getLocation().getCity();
//        String language = hotelDto.getLanguage();
//        Criteria criteria = HibernateUtil.getSession().createCriteria(Room.class);
//        criteria
//                .createAlias(AliasName.HOTEL, AliasValue.HOTEL)
//                .createAlias(AliasName.LOCATION, AliasValue.LOCATION)
//                .createAlias(AliasName.ROOMTYPE, AliasValue.ROOMTYPE)
//                .createAlias(AliasName.BILLSET, AliasValue.BILLSET);
//        try {
//            transaction.begin();
//            if (hotelId != null) {
//                criteria.add(Restrictions.eq(SearchParameter.HOTEL_HOTELID, hotelId));
//            } else {
//                if (StringUtils.isNotBlank(localizedCountry)) {
//                    String country = ServiceHelper.getNotLocalizedCountry(localizedCountry, language);
//                    criteria.add(Restrictions.eq(SearchParameter.HOTEL_LOCATION_COUNTRY, country));
//                    if (StringUtils.isNotBlank(localizedCity)) {
//                        String city = ServiceHelper.getNotLocalizedCity(localizedCity, language);
//                        criteria.add(Restrictions.eq(SearchParameter.HOTEL_LOCATION_CITY, city));
//                    }
//                }
//            }
//            Integer totalPersons = orderDto.getTotalPersons();
//            Integer totalRooms = orderDto.getTotalRooms();
//            Integer maxPersons = Math.round(totalPersons / totalRooms);
//            maxPersons = (maxPersons <= 0) ? 1 : maxPersons;
//            criteria.add(Restrictions.ge(SearchParameter.ROOMTYPE_MAXPERSONS, maxPersons));
//            Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
//            Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
//
//            Conjunction condition1 = Restrictions.conjunction();
//            condition1
//                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkInDate))
//                    .add(Restrictions.ge(SearchParameter.BILLSET_CHECKOUTDATE, checkOutDate));
//            Conjunction condition2 = Restrictions.conjunction();
//            condition2
//                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
//                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkOutDate));
//            Conjunction condition3 = Restrictions.conjunction();
//            condition3
//                    .add(condition1)
//                    .add(condition2);
//            Conjunction condition4 = Restrictions.conjunction();
//            condition4
//                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
//                    .add(Restrictions.between(SearchParameter.BILLSET_CHECKINDATE, checkInDate, checkOutDate));
//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction
//                    .add(condition3)
//                    .add(condition4);
//
//            List<Room> roomList = roomDao.getListByCriteria(criteria, page, perPage);
//            roomDtoList = converter.toDtoList(roomList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return roomDtoList;
//    }

}
