package by.kanarski.booking.utils;


import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.*;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.filter.SearchFilter;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EntityBuilder {
    
    @Autowired
    private IUserDao userDao;

    @Autowired
    private ILocationDao locationDao;

    @Autowired
    private IHotelDao hotelDao;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IRoomTypeDao roomTypeDao;

    @Autowired
    private IBillDao billDao;

    @Autowired
    private IFacilityDao facilityDao;

    @Autowired
    private ServiceHelper serviceHelper;

    @Autowired
    private SystemLanguagesManager systemLanguagesManager;

    public User buildUser(Long userId, String firstName, String lastName, String email, String login,
                          String password, Set<State> roleSet, State userStatus) throws DaoException {
        User user;
        if (userId != null) {
            user = userDao.getById(userId);
        } else {
            user = new User();
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUserName(login);
        user.setPassword(password);
        user.setRoleSet(roleSet);
        user.setUserStatus(userStatus);
        return user;
    }

    public Location buildLocation(Long locationId, String country, String city, State locationStatus) {
        Location location = null;
        try {
            String language = UserPreferences.getRequestedLocale().getLanguage();
            Long languageId = systemLanguagesManager.getLanguageId(language);
            Map<Long, LocationTranslation> locationTranslationMap;
            if (locationId != null) {
                location = locationDao.getById(locationId);
                if (location != null) {
                    locationTranslationMap = location.getLocationTranslationMap();
                } else {
                    locationTranslationMap = new HashMap<>();
                }
            } else {
                location = new Location();
                locationTranslationMap = new HashMap<>();
            }
            String notLocalizedCountry = serviceHelper.getNotLocalizedCountry(country, language);
            String notLocalizedCity = serviceHelper.getNotLocalizedCity(city, language);
            LocationTranslation locationTranslation = new LocationTranslation();
            locationTranslation.setCountry(country);
            locationTranslation.setCity(city);
            locationTranslationMap.put(languageId, locationTranslation);
            location.setCountry(notLocalizedCountry);
            location.setCity(notLocalizedCity);
            location.setLocationStatus(locationStatus);
        } catch (DaoException e) {

        }
        return location;
    }

    public Hotel buildHotel(Long hotelId, Location location, String hotelName, State hotelStatus) {
        Hotel hotel = null;
        try {
            String language = UserPreferences.getRequestedLocale().getLanguage();
            Long languageId = systemLanguagesManager.getLanguageId(language);
            Map<Long, HotelTranslation> hotelTranslationMap;
            if (hotelId != null) {
                hotel = hotelDao.getById(hotelId);
                if (hotel != null) {
                    hotelTranslationMap = hotel.getHotelTranslationMap();
                } else {
                    hotelTranslationMap = new HashMap<>();
                }
            } else {
                hotel = new Hotel();
                hotelTranslationMap = new HashMap<>();
            }
            String notLocalizedHotelName = serviceHelper.getNotLocalizedHotelName(hotelName, language);
            HotelTranslation hotelTranslation = new HotelTranslation();
            hotelTranslation.setHotelName(hotelName);
            hotelTranslationMap.put(languageId, hotelTranslation);
            hotel.setHotelName(notLocalizedHotelName);
            hotel.setLocation(location);
            hotel.setHotelStatus(hotelStatus);
        } catch (DaoException e) {

        }
        return hotel;
    }

    public RoomType buildRoomType(Long roomTypeId, String roomTypeName, Integer maxPersons, Double pricePerNight,
                                         Set<Facility> facilitySet, State roomTypeStatus) {
        RoomType roomType = null;
        try {
            String language = UserPreferences.getRequestedLocale().getLanguage();
            Long languageId = systemLanguagesManager.getLanguageId(language);
            Map<Long, RoomTypeTranslation> roomTypeTranslationMap;
            if (roomTypeId != null) {
                roomType = roomTypeDao.getById(roomTypeId);
                if (roomType != null) {
                    roomTypeTranslationMap = roomType.getRoomTypeTranslationMap();
                } else {
                    roomTypeTranslationMap = new HashMap<>();
                }
            } else {
                roomType = new RoomType();
                roomTypeTranslationMap = new HashMap<>();
            }
            String notLocalizedRoomTypeName = serviceHelper.getNotLocalizedRoomTypeName(roomTypeName, language);
            RoomTypeTranslation roomTypeTranslation = new RoomTypeTranslation();
            roomTypeTranslation.setRoomTypeName(roomTypeName);
            roomTypeTranslationMap.put(languageId, roomTypeTranslation);
            roomType.setRoomTypeName(notLocalizedRoomTypeName);
            roomType.setMaxPersons(maxPersons);
            roomType.setPricePerNight(pricePerNight);
            roomType.setFacilitySet(facilitySet);
            roomType.setRoomTypeStatus(roomTypeStatus);
        } catch (DaoException e) {

        }
        return roomType;
    }

    public Room buildRoom(Long roomId, Hotel hotel, RoomType roomType, Integer roomNumber, State roomStatus) {
        Room room = null;
        try {
            if (roomId != null) {
                room = roomDao.getById(roomId);
            } else {
                room = new Room();
            }
            room.setHotel(hotel);
            room.setRoomType(roomType);
            room.setRoomNumber(roomNumber);
            room.setRoomStatus(roomStatus);
        } catch (DaoException e) {

        }
        return room;
    }

    public Bill buildBill(Long billId, User client, Long bookingDate, Integer totalPersons, Long checkInDate,
                          Long checkOutDate, Set<Room> roomSet, Double paymentAmount, State paymentStatus, State billStatus) {
        Bill bill = null;
        try {
            if (billId != null) {
                bill = billDao.getById(billId);
            } else {
                bill = new Bill();
            }
            bill.setUser(client);
            bill.setBookingDate(bookingDate);
            bill.setTotalPersons(totalPersons);
            bill.setCheckInDate(checkInDate);
            bill.setCheckOutDate(checkOutDate);
            bill.setRoomSet(roomSet);
            bill.setPaymentAmount(paymentAmount);
            bill.setPaymentStatus(paymentStatus);
            bill.setBillStatus(billStatus);
        } catch (DaoException e) {

        }
        return bill;
    }

    public Facility buildFacility(Long facilityId, String facilityName, State facilityStatus) {
        Facility facility = null;
        try {
            String language = UserPreferences.getRequestedLocale().getLanguage();
            Long languageId = systemLanguagesManager.getLanguageId(language);
            Map<Long, FacilityTranslation> facilityTranslationMap;
            if (facilityId != null) {
                facility = facilityDao.getById(facilityId);
                facilityTranslationMap = facility.getFacilityTranslationMap();
                if (MapUtils.isEmpty(facilityTranslationMap)) {
                    facilityTranslationMap = new HashMap<>();
                }
            } else {
                facility = new Facility();
                facilityTranslationMap = new HashMap<>();
            }
            String notLocalizedFacilityName = serviceHelper.getNotLocalizedFacilityName(facilityName, language);
            FacilityTranslation facilityTranslation = new FacilityTranslation();
            facilityTranslation.setFacilityName(facilityName);
            facilityTranslationMap.put(languageId, facilityTranslation);
            facility.setFacilityName(notLocalizedFacilityName);
            facility.setFacilityStatus(facilityStatus);
        } catch (DaoException e) {

        }
        return facility;
    }

    public Facility buildFacility(String facilityName) {
        Facility facility = null;
        try {
            String language = UserPreferences.getRequestedLocale().getLanguage();
            Long languageId = systemLanguagesManager.getLanguageId(language);
            String notLocalizedFacilityName = serviceHelper.getNotLocalizedFacilityName(facilityName, language);
            SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.FACILITYNAME, notLocalizedFacilityName);
            facility = facilityDao.getUniqueByFilter(searchFilter);
        } catch (DaoException e) {

        }
        return facility;
    }

}
