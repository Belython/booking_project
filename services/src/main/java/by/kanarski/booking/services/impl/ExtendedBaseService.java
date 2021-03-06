package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IExtendedBaseService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.DtoToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ExtendedBaseService<E, D> implements IExtendedBaseService<E, D> {

    @Autowired
    private IExtendedBaseDao<E> extendedBaseDao;

    protected DtoToEntityConverter<E, D> converter = new DtoToEntityConverter<>(getEntityClass(), getDtoClass());

    protected DtoToEntityConverter<Location, LocationDto> locationConverter =
            new DtoToEntityConverter<>(Location.class, LocationDto.class);
    protected DtoToEntityConverter<Hotel, HotelDto> hotelConverter =
            new DtoToEntityConverter<>(Hotel.class, HotelDto.class);
    protected DtoToEntityConverter<Hotel, UserHotelDto> userHotelConverter =
            new DtoToEntityConverter<>(Hotel.class, UserHotelDto.class);
    protected DtoToEntityConverter<RoomType, RoomTypeDto> roomTypeConverter =
            new DtoToEntityConverter<>(RoomType.class, RoomTypeDto.class);
    protected DtoToEntityConverter<Room, RoomDto> roomConverter =
            new DtoToEntityConverter<>(Room.class, RoomDto.class);
    protected DtoToEntityConverter<Bill, BillDto> billConverter =
            new DtoToEntityConverter<>(Bill.class, BillDto.class);
    protected DtoToEntityConverter<User, UserDto> userConverter =
            new DtoToEntityConverter<>(User.class, UserDto.class);

    @Override
    public void add(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.add(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public D getById(Long id) throws ServiceException {
        setEntityClass();
        D dto = null;
        try {
            E entity = extendedBaseDao.getById(id);
            dto = converter.toDto(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return dto;
    }

    @Override
    public void update(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.update(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void delete(D dto) throws ServiceException {
        setEntityClass();
        try {
            E entity = converter.toEntity(dto);
            extendedBaseDao.delete(entity);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public List<D> getAll() throws ServiceException {
        setEntityClass();
        List<D> dtoList = null;
        try {
            List<E> enittyList = extendedBaseDao.getAll();
            dtoList = converter.toDtoList(enittyList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
        return dtoList;
    }


    @Override
    public void updateList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.updateList(entityList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    @Override
    public void addList(List<D> dtoList) throws ServiceException {
        setEntityClass();
        try {
            List<E> entityList = converter.toEntityList(dtoList);
            extendedBaseDao.addList(entityList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            BookingExceptionHandler.handleLocalizationException(e);
        }
    }

    private void setEntityClass() {
//        getConverter();
        extendedBaseDao.setEntityClass(getEntityClass());
    }


    protected Class<E> getEntityClass() {
        Type superclass = getClass().getGenericSuperclass();
        Class<E> persistentClass = null;
        if (!superclass.equals(Object.class)) {
            ParameterizedType classType = (ParameterizedType) superclass;
            persistentClass = (Class<E>) classType.getActualTypeArguments()[0];
        }
        return persistentClass;
    }

    protected Class<D> getDtoClass() {
        Type superclass = getClass().getGenericSuperclass();
        Class<D> persistentClass = null;
        if (!superclass.equals(Object.class)) {
            ParameterizedType classType = (ParameterizedType) superclass;
            persistentClass = (Class<D>) classType.getActualTypeArguments()[1];
        }
        return persistentClass;
    }

}
