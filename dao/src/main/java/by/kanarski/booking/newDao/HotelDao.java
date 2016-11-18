package by.kanarski.booking.newDao;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.newDao.interfaces.IHotelDao;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao implements IHotelDao {

    private static HotelDao instance = null;
    private final String ADD_QUERY = "INSERT INTO HOTELS (LOCATION_ID, HOTEL_NAME, HOTEL_STATUS) VALUES(" +
            "(SELECT LOCATION_ID FROM LOCATIONS WHERE COUNTRY = ? AND CITY = ?), ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_ID = ?";
    private final String GET_ALL_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID";
    private final String GET_BY_HOTEL_NAME_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_NAME = ?";
    private final String UPDATE_QUERY = "UPDATE HOTELS " +
            "SET LOCATION_ID = ?, HOTEL_NAME = ?, HOTEL_STATUS = ? WHERE HOTEL_ID = ?";
    private final String DELETE_QUERY = "DELETE FROM HOTELS WHERE HOTEL_ID = ?";
    private final String GET_BY_COUNTRY_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE L.COUNTRY = ?";
    private final String GET_BY_CITY_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE L.CITY = ?";


    private HotelDao() {
    }

    public static synchronized HotelDao getInstance() {
        if (instance == null) {
            instance = new HotelDao();
        }
        return instance;
    }

    @Override
    public Hotel add(Hotel hotel) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, hotel.getLocation().getCountry());
            stm.setString(2, hotel.getLocation().getCity());
            stm.setString(3, hotel.getHotelName());
            stm.setString(4, hotel.getHotelStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            hotel.setHotelId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.ADD_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    @Override
    public Hotel getById(long id) throws DaoException {
        Hotel hotel = null;
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            resultSet = stm.executeQuery();
            resultSet.next();
            hotel = EntityParser.parseHotel(resultSet);
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    @Override
    public void update(Hotel hotel) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setLong(1, hotel.getLocation().getLocationId());
            stm.setString(2, hotel.getHotelName());
            stm.setString(3, hotel.getHotelStatus());
            stm.setLong(4, hotel.getHotelId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.UPDATE_EXCEPTION.get(), e);
        }
    }

    @Override
    public void delete(Hotel hotel) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(DELETE_QUERY)) {
            stm.setLong(1, hotel.getHotelId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.DELETE_EXCEPTION.get(), e);
        }
    }

    public void updateList(List<Hotel> hotelList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            for (Hotel hotel : hotelList) {
                stm.setLong(1, hotel.getLocation().getLocationId());
                stm.setString(2, hotel.getHotelName());
                stm.setString(3, hotel.getHotelStatus());
                stm.setLong(4, hotel.getHotelId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.UPDATE_EXCEPTION.get(), e);
        }
    }

    @Override
    public List<Hotel> getAll() throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public Hotel getByHotelName(String hotelName) throws DaoException {
        Hotel hotel = null;
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_HOTEL_NAME_QUERY)) {
            stm.setString(1, hotelName);
            resultSet = stm.executeQuery();
            resultSet.next();
            hotel = EntityParser.parseHotel(resultSet);
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    public List<Hotel> getByCountry(String country) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_COUNTRY_QUERY)) {
            stm.setString(1, country);
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public List<Hotel> getByCity(String city) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_CITY_QUERY)) {
            stm.setString(1, city);
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public void addList(List<Hotel> hotelList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Hotel hotel: hotelList) {
                stm.setString(1, hotel.getLocation().getCountry());
                stm.setString(2, hotel.getLocation().getCity());
                stm.setString(3, hotel.getHotelName());
                stm.setString(4, hotel.getHotelStatus());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.ADD_EXCEPTION.get(), e);
        }
    }
}
