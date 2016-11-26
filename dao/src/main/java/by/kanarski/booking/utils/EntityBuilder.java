package by.kanarski.booking.utils;


import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dao.impl.*;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.managers.SupportedLanguagesManager;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import by.kanarski.booking.utils.wrappers.SupportedLanguages;

import java.util.*;

public class EntityBuilder {

    private static SupportedLanguages supportedLanguages = SupportedLanguagesManager.get();
    private static String currentLanguage = UserPreferences.getLocale().getLanguage();
    private static Long currentLanguageId = Integer.toUnsignedLong(supportedLanguages.indexOf(currentLanguage));

    private EntityBuilder() {
    }

    public static User buildUser(Long userId, String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) throws DaoException {
        User user;
        if (userId != null) {
            user = UserDao.getInstance().getById(userId);
        } else {
            user = new User();
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setUserStatus(userStatus);
        return user;
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role) throws DaoException {
        return buildUser(null, firstName, lastName, email, login, password, role, FieldValue.STATUS_ACTIVE);
    }

    public static User buildUser(Long userId, String firstName, String lastName, String email, String login,
                                 String password, String role) throws DaoException {
        return buildUser(userId, firstName, lastName, email, login, password, role, FieldValue.STATUS_ACTIVE);
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) throws DaoException {
        return buildUser(null, firstName, lastName, email, login, password, role, userStatus);
    }

    public static Location buildLocation(Long locationId, String country, String city, String locationStatus)
            throws DaoException {
        Location location;
        Map<Long, LocationTranslation> locationTranslationMap;
        if (locationId != null) {
            location = LocationDao.getInstance().getById(locationId);
            if (location != null) {
                locationTranslationMap = location.getLocationTranslationMap();
            } else {
                locationTranslationMap = new HashMap<>();
            }
        } else {
            location = new Location();
            locationTranslationMap = new HashMap<>();
        }
        LocationTranslation locationTranslation = new LocationTranslation();
        locationTranslation.setCountry(country);
        locationTranslation.setCity(city);
        locationTranslationMap.put(currentLanguageId, locationTranslation);
        location.setLocationStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(String country, String city) throws DaoException {
        return buildLocation(null, country, city, FieldValue.STATUS_ACTIVE);
    }

    public static Location buildLocation(Long locationId, String country, String city) throws DaoException {
        return buildLocation(locationId, country, city, FieldValue.STATUS_ACTIVE);
    }

    public static Location buildLocation(String country, String city, String locationStatus) throws DaoException {
        return buildLocation(null, country, city, locationStatus);
    }


    public static Hotel buildHotel(Long hotelId, Location location, String hotelName, String hotelStatus)
            throws DaoException {
        Hotel hotel;
        Map<Long, HotelTranslation> hotelTranslationMap;
        if (hotelId != null) {
            hotel = HotelDao.getInstance().getById(hotelId);
            if (hotel != null) {
                hotelTranslationMap = hotel.getHotelTranslationMap();
            } else {
                hotelTranslationMap = new HashMap<>();
            }
        } else {
            hotel = new Hotel();
            hotelTranslationMap = new HashMap<>();
        }
        HotelTranslation hotelTranslation = new HotelTranslation();
        hotelTranslation.setHotelName(hotelName);
        hotelTranslationMap.put(currentLanguageId, hotelTranslation);
        hotel.setLocation(location);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(Location location, String hotelName) throws DaoException {
        return buildHotel(null, location, hotelName, FieldValue.STATUS_ACTIVE);
    }

    public static Hotel buildHotel(Long hotelId, Location location, String hotelName) throws DaoException {
        return buildHotel(hotelId, location, hotelName, FieldValue.STATUS_ACTIVE);
    }

    public static Hotel buildHotel(Location location, String hotelName, String hotelStatus) throws DaoException {
        return buildHotel(null, location, hotelName, hotelStatus);
    }

    public static RoomType buildRoomType(Long roomTypeId, String roomTypeName, int maxPersons, double pricePerNight,
                                         Set<Facility> facilitySet, String roomTypeStatus) throws DaoException {
        RoomType roomType;
        Map<Long, RoomTypeTranslation> roomTypeTranslationMap;
        if (roomTypeId != null) {
            roomType = RoomTypeDao.getInstance().getById(roomTypeId);
            if (roomType != null) {
                roomTypeTranslationMap = roomType.getRoomTypeTranslationMap();
            } else {
                roomTypeTranslationMap = new HashMap<>();
            }
        } else {
            roomType = new RoomType();
            roomTypeTranslationMap = new HashMap<>();
        }
        RoomTypeTranslation roomTypeTranslation = new RoomTypeTranslation();
        roomTypeTranslation.setRoomTypeName(roomTypeName);
        roomTypeTranslationMap.put(currentLanguageId, roomTypeTranslation);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(pricePerNight);
        roomType.setFacilitySet(facilitySet);
        roomType.setRoomTypeStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, Integer maxPersons, Double pricePerNight,
                                         Set<Facility> facilitySet) throws DaoException {
        return buildRoomType(null, roomTypeName, maxPersons, pricePerNight,
                facilitySet, FieldValue.STATUS_ACTIVE);
    }

    public static RoomType buildRoomType(Long roomTypeId, String roomTypeName, Integer maxPersons,
                                         Double pricePerNight, Set<Facility> facilitySet) throws DaoException {
        return buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNight, facilitySet, FieldValue.STATUS_ACTIVE);
    }

    public static RoomType buildRoomType(String roomTypeName, Integer maxPersons, Double pricePerNight,
                                         Set<Facility> facilitySet, String roomTypeStatus) throws DaoException {
        return buildRoomType(null, roomTypeName, maxPersons, pricePerNight, facilitySet, roomTypeStatus);
    }

    public static Room buildRoom(Long roomId, Hotel hotel, RoomType roomType, Integer roomNumber, String roomStatus)
            throws DaoException {
        Room room;
        if (roomId != null) {
            room = RoomDao.getInstance().getById(roomId);
        } else {
            room = new Room();
        }
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, Integer roomNumber) throws DaoException {
        return buildRoom(null, hotel, roomType, roomNumber, FieldValue.STATUS_ACTIVE);
    }

    public static Room buildRoom(Long roomId, Hotel hotel, RoomType roomType, Integer roomNumber) throws DaoException {
        return buildRoom(roomId, hotel, roomType, roomNumber, FieldValue.STATUS_ACTIVE);

    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, String roomStatus) throws DaoException {
        return buildRoom(null, hotel, roomType, roomNumber, roomStatus);
    }

    public static Bill buildBill(Long billId, User client, Integer totalPersons, Long checkInDate, Long checkOutDate,
                                Set<Room> roomSet, Double paymentAmount, String billStatus) throws DaoException {
        Bill bill;
        if (billId != null) {
            bill = BillDao.getInstance().getById(billId);
        } else {
            bill = new Bill();
        }
        bill.setClient(client);
        bill.setTotalPersons(totalPersons);
        bill.setCheckInDate(checkInDate);
        bill.setCheckOutDate(checkOutDate);
        bill.setRoomSet(roomSet);
        bill.setPaymentAmount(paymentAmount);
        bill.setBillStatus(billStatus);
        return bill;
    }

    public static Bill buildBill(User user, Integer totalPersons, Long checkInDate, Long checkOutDate,
                                 Set<Room> roomSet, Double paymentAmount) throws DaoException {
        return buildBill(null, user, totalPersons, checkInDate, checkOutDate, roomSet,
                paymentAmount, FieldValue.STATUS_NOT_PAID);
    }

}
