package by.kanarski.booking.newDao;

import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.newDao.interfaces.IRoomTypeDao;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;
import by.kanarski.booking.utils.SerializationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao implements IRoomTypeDao {

    private static RoomTypeDao instance = null;

    private final String ADD_QUERY = "INSERT INTO ROOMS_TYPES (" +
            "ROOM_TYPE, MAX_PERSONS, PRICE_PER_NIGHT, FACILITIES, ROOM_TYPE_STATUS)" +
            "VALUES(?, ?, ?, ?, ?);";
    private final String GET_BY_ID_QUERY = "SELECT * FROM ROOMS_TYPES WHERE ROOM_TYPE_ID = ?;";
    private final String GET_ALL_QUERY = "SELECT * FROM ROOMS_TYPES;";
    private final String UPDATE_QUERY = "UPDATE ROOMS_TYPES " +
            "SET ROOM_TYPE = ?, MAX_PERSONS = ?, PRICE_PER_NIGHT = ?, FACILITIES = ?, ROOM_TYPE_STATUS = ? " +
            "WHERE ROOM_TYPE_ID = ?;";
    private final String DELETE_QUERY = "DELETE FROM ROOM_TYPE WHERE ROOM_TYPE_ID = ?;";

    private RoomTypeDao() {
    }

    public static synchronized RoomTypeDao getInstance() {
        if (instance == null) {
            instance = new RoomTypeDao();
        }
        return instance;
    }

    @Override
    public RoomType add(RoomType roomType) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, roomType.getRoomTypeName());
            stm.setInt(2, roomType.getMaxPersons());
            stm.setDouble(3, roomType.getPricePerNight());
            stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
            stm.setString(5, roomType.getRoomTypeStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            roomType.setRoomTypeId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.ADD_EXCEPTION.get(), e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return roomType;
    }

    @Override
    public RoomType getById(long id) throws DaoException {
        RoomType roomType = null;
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            roomType = EntityParser.parseRoomType(resultSet);
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        }
        return roomType;
    }

    @Override
    public List<RoomType> getAll() throws DaoException {
        List<RoomType> roomTypes = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                roomTypes.add(EntityParser.parseRoomType(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
        }
        return roomTypes;
    }

    @Override
    public void update(RoomType roomType) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setString(1, roomType.getRoomTypeName());
            stm.setInt(2, roomType.getMaxPersons());
            stm.setDouble(3, roomType.getPricePerNight());
            stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
            stm.setString(5, roomType.getRoomTypeStatus());
            stm.setLong(6, roomType.getRoomTypeId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.UPDATE_EXCEPTION.get(), e);
        }
    }

    @Override
    public void delete(RoomType roomType) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(DELETE_QUERY)) {
            stm.setLong(1, roomType.getRoomTypeId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.DELETE_EXCEPTION.get(), e);
        }
    }

    @Override
    public void updateList(List<RoomType> roomTypeList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            for (RoomType roomType : roomTypeList) {
                stm.setString(1, roomType.getRoomTypeName());
                stm.setInt(2, roomType.getMaxPersons());
                stm.setDouble(3, roomType.getPricePerNight());
                stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
                stm.setString(5, roomType.getRoomTypeStatus());
                stm.setLong(6, roomType.getRoomTypeId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.UPDATE_EXCEPTION.get(), e);
        }
    }

    @Override
    public void addList(List<RoomType> roomTypeList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            for (RoomType roomType : roomTypeList) {
                stm.setString(1, roomType.getRoomTypeName());
                stm.setInt(2, roomType.getMaxPersons());
                stm.setDouble(3, roomType.getPricePerNight());
                stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
                stm.setString(5, roomType.getRoomTypeStatus());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessageManager.ADD_EXCEPTION.get(), e);
        }
    }

}
