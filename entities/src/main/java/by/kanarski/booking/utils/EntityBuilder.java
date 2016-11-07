package by.kanarski.booking.utils;


import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.entities.*;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class EntityBuilder {

    private EntityBuilder() {
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role) {
        User user = new User();
        user.setUserId(FieldValue.UNDEFINED_ID);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setUserStatus(FieldValue.STATUS_ACTIVE);
        return user;
    }

    public static User buildUser(long userId, String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setUserId(userId);
        user.setUserStatus(userStatus);
        return user;
    }

    public static User buildUser(long userId, String firstName, String lastName, String email, String login,
                                 String password, String role) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setUserId(userId);
        return user;
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setUserStatus(userStatus);
        return user;
    }

    public static Location buildLocation(String country, String city) {
        Location location = new Location();
        location.setLocationId(FieldValue.UNDEFINED_ID);
        location.setCountry(country);
        location.setCity(city);
        location.setLocationStatus(FieldValue.STATUS_ACTIVE);
        return location;
    }

    public static Location buildLocation(long locationId, String country, String city, String locationStatus) {
        Location location = buildLocation(country, city);
        location.setLocationId(locationId);
        location.setLocationStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(long locationId, String country, String city) {
        Location location = buildLocation(country, city);
        location.setLocationId(locationId);
        return location;
    }

    public static Location buildLocation(String country, String city, String locationStatus) {
        Location location = buildLocation(country, city);
        location.setLocationStatus(locationStatus);
        return location;
    }


    public static Hotel buildHotel(Location hotelLocation, String hotelName) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(FieldValue.UNDEFINED_ID);
        hotel.setLocation(hotelLocation);
        hotel.setHotelName(hotelName);
        hotel.setHotelStatus(FieldValue.STATUS_ACTIVE);
        return hotel;
    }

    public static Hotel buildHotel(long hotelId, Location hotelLocation, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelLocation, hotelName);
        hotel.setHotelId(hotelId);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(long hotelId, Location hotelLocation, String hotelName) {
        Hotel hotel = buildHotel(hotelLocation, hotelName);
        hotel.setHotelId(hotelId);
        return hotel;
    }

    public static Hotel buildHotel(Location hotelLocation, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelLocation, hotelName);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeId(FieldValue.UNDEFINED_ID);
        roomType.setRoomTypeName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setRoomTypeStatus(FieldValue.STATUS_ACTIVE);
        return roomType;
    }

    public static RoomType buildRoomType(long roomTypeId, String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setRoomTypeId(roomTypeId);
        roomType.setRoomTypeStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(long roomTypeId, String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setRoomTypeId(roomTypeId);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setRoomTypeStatus(roomTypeStatus);
        return roomType;
    }


    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, TreeMap<Long, Long> bookedDates) {
        Room room = new Room();
        room.setRoomId(FieldValue.UNDEFINED_ID);
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setBookedDates(bookedDates);
        room.setRoomStatus(FieldValue.STATUS_ACTIVE);
        return room;
    }

    public static Room buildRoom(long roomId, Hotel hotel, RoomType roomType, int roomNumber,
                                 TreeMap<Long, Long> bookedDates, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookedDates);
        room.setRoomId(roomId);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static Room buildRoom(long roomId, Hotel hotel, RoomType roomType, int roomNumber,
                                 TreeMap<Long, Long> bookedDates) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookedDates);
        room.setRoomId(roomId);
        return room;
    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, TreeMap<Long, Long> bookedDates, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookedDates);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static Bill buildBill(User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, double paymentAmount) {
        Bill bill = new Bill();
        bill.setBillId(FieldValue.UNDEFINED_ID);
        bill.setClient(user);
        bill.setTotalPersons(totalPersons);
        bill.setCheckInDate(checkInDate);
        bill.setCheckOutDate(checkOutDate);
        bill.setBookedRoomIdList(roomIdList);
        bill.setPaymentAmount(paymentAmount);
        bill.setBillStatus(FieldValue.STATUS_NOT_PAID);
        return bill;
    }

    public static Bill buildBill(long billId, User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, double paymentAmount, String billStatus) {
        Bill bill = buildBill(user, totalPersons, checkInDate, checkOutDate, roomIdList, paymentAmount);
        bill.setBillId(billId);
        bill.setBillStatus(billStatus);
        return bill;
    }

}
