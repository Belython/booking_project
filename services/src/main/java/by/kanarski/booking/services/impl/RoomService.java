package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.LocationTranslationDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomService;
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import by.kanarski.booking.utils.wrappers.SearchFilter;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class RoomService extends ExtendedBaseService<Room, RoomDto> implements IRoomService {

    private static RoomService instance;
    private RoomDao roomDao = RoomDao.getInstance();

    private RoomService() {
    }

    public static synchronized RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService();
        }
        return instance;
    }

    @Override
    public List<RoomDto> getAvailableRooms(OrderDto orderDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<RoomDto> roomDtoList = null;
        HotelDto hotelDto = orderDto.getHotel();
        Long hotelId = hotelDto.getHotelId();
        String country = hotelDto.getLocation().getCountry();
        String city = hotelDto.getLocation().getCity();
        SearchFilter roomFilter = new SearchFilter();
        try {
            transaction.begin();
            if (hotelId != null) {
                roomFilter.setEqFilter("hotel.hotelId", hotelId);
            } else {
                if (StringUtils.isNotBlank(country)) {
                    SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
                    if (StringUtils.isNotBlank(city)) {
                        locationFilter.setEqFilter("city", city);
                    }
                    locationFilter.setEqFilter("language", "EN");
                    List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
                            .getListByFilter(roomFilter);
                    if (locationTranslationList.size() > 0) {
                        LocationTranslation locationTranslation = locationTranslationList.get(0);
                        if (locationTranslationList.size() == 1) {
                            Long locationId = locationTranslation.getLocation().getLocationId();
                            roomFilter.setEqFilter("hotel.location.locationId", locationId);
                        } else {
                            country = locationTranslation.getCountry();
                            roomFilter.setEqFilter("hotel.location.country", country);
                        }
                    }
                }
            }
            Integer totalPersons = orderDto.getTotalPersons();
            roomFilter.setGeFilter("roomType.maxPersons", totalPersons);
            Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
            Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
            List<Room> roomList = roomDao.getListByFilter(roomFilter, page, perPage);
            roomDtoList = converter.toDtoList(roomList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getByHotelId(Long hotelId, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<RoomDto> roomDtoList = null;
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter("hotel.hotelId", hotelId);
        try {
            transaction.begin();
            List<Room> roomList = roomDao.getListByFilter(searchFilter, page, perPage);
            roomDtoList = converter.toDtoList(roomList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
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
