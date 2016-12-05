package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IRoomDao;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomService;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RoomService extends ExtendedBaseService<Room, RoomDto> implements IRoomService {

    @Autowired
    private IRoomDao roomDao;

    @Override
    public List<RoomDto> getAvailableRooms(OrderDto orderDto, int page, int perPage) throws ServiceException {
        List<RoomDto> roomDtoList = null;
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
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getByHotelId(Long hotelId, int page, int perPage) throws ServiceException {
        List<RoomDto> roomDtoList = null;
        SearchFilter searchFilter = SearchFilter
                .createAliasFilter(AliasName.HOTEL, AliasValue.HOTEL)
                .addEqFilter(SearchParameter.HOTEL_HOTELID, hotelId);
        try {
            List<Room> roomList = roomDao.getListByFilter(searchFilter, page, perPage);
            roomDtoList = converter.toDtoList(roomList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return roomDtoList;
    }

//    @Override
//    public List<RoomDto> getByBill(BillDto billDto, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<RoomDto> roomDtoList = null;
//        Bill bill = DtoToEntityConverter.toBill(billDto);
//        try {
//            transaction.begin();
//            List<Room> roomList = roomDao.getByIdList(bill.getBookedRoomIdList());
//            roomDtoList = DtoToEntityConverter.toRoomDtoList(roomList);
//            transaction.commit();
//        } catch (SQLException | LocalisationException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return roomDtoList;
//    }


}
