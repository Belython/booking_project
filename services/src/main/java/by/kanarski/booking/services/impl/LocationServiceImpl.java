package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.LocationDao;
import by.kanarski.booking.dto.LocationDto;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.ILocationService;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LocationServiceImpl implements ILocationService {

    private static LocationServiceImpl instance;
    private static LocationDao locationDao = LocationDao.getInstance();

    private LocationServiceImpl() {
    }

    public static synchronized LocationServiceImpl getInstance() {
        if (instance == null) {
            instance = new LocationServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(LocationDto locationDto) throws ServiceException {

    }

    @Override
    public List<LocationDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<LocationDto> locationDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Location> locationList = locationDao.getAll();
            locationDtoList = DtoToEntityConverter.toLocationDtoList(locationList);
            connection.commit();
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return locationDtoList;
    }

    @Override
    public LocationDto getById(long id) throws ServiceException {
        return null;
    }

    @Override
    public void update(LocationDto locationDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            Location location = DtoToEntityConverter.toLocation(locationDto);
            locationDao.update(location);
            connection.commit();
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    @Override
    public void updateList(List<LocationDto> locationDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Location> locationList = DtoToEntityConverter.toLocationList(locationDtoList);
            locationDao.updateList(locationList);
            connection.commit();
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void addList(List<LocationDto> locationDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Location> locationList = DtoToEntityConverter.toLocationList(locationDtoList);
            locationDao.addList(locationList);
            connection.commit();
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
