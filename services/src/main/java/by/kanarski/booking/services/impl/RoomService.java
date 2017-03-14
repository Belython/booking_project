package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.dao.interfaces.IRoomDao;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomService;
import by.kanarski.booking.utils.BookingExceptionHandler;
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
    public List<RoomDto> getByHotelId(Long hotelId, int page, int perPage) throws ServiceException {
        List<RoomDto> roomDtoList = null;
        SearchFilter searchFilter = SearchFilter
                .createAliasFilter(AliasName.HOTEL, AliasValue.HOTEL)
                .addEqFilter(SearchParameter.HOTEL_HOTELID, hotelId);
        try {
            List<Room> roomList = roomDao.getListByFilter(searchFilter, page, perPage);
            roomDtoList = conversionService.convert(roomList, RoomDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return roomDtoList;
    }

    @Override
    protected IExtendedBaseDao<Room> getDao() {
        return roomDao;
    }
}
