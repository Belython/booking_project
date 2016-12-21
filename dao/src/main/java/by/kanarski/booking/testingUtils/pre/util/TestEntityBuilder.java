package by.kanarski.booking.testingUtils.pre.util;

import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Language;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.testingUtils.pre.jsonHandler.JsonManager;

import java.util.*;

import static by.kanarski.booking.constants.FieldValue.ROLE_USER;
import static by.kanarski.booking.constants.FieldValue.STATUS_ACTIVE;

public class TestEntityBuilder {

    private static final int EXPECTED = 0;
    private static final int UPDATED = 0;
    private static final int USERS_AMOUNT = 10;
    private static final int UNIQUE_VALUES = 10;
    private static final int LOCATIONS_AMOUNT = 10;
    private static final int LANGUAGES_AMOUNT = 3;
    private static final int LOCATION_TRANSLATIONS_AMOUNT = LOCATIONS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int HOTELS_PER_LOCATION = 3;
    private static final int HOTELS_AMOUNT = 5 * HOTELS_PER_LOCATION;
    private static final int HOTEL_TRANSLATIONS_AMOUNT = HOTELS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int FACILITYS_AMOUNT = 10;
    private static final int FACILITY_TRANSLATIONS_AMOUNT = FACILITYS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int ROOM_TYPES_AMOUNT = 10;
    private static final int ROOM_TYPE_TRANSLATIONS_AMOUNT = ROOM_TYPES_AMOUNT * LANGUAGES_AMOUNT;
    private static final int FACILITIES_PER_ROOM_TYPE = 4;
    private static final int ROOMS_PER_HOTEL = 5;
    private static final int ROOMS_AMOUNT = HOTELS_AMOUNT * ROOMS_PER_HOTEL;
    private static final int BILLS_PER_USER = 2;
    private static final int BILLS_AMOUNT = USERS_AMOUNT * BILLS_PER_USER;
    private static final int ROOMS_PER_BILL = 3;

    private static final Long DAYS10 = 864000000L;

    //User
    public static final User NEW_USER = getNewUser();
    public static final List<User> NEW_USER_LIST = getNewUserList();
    public static final List<User> EXPECTED_USER_LIST = getExpectedUserList();
    public static final User EXPECTED_USER = getExpectedUser();
    public static final User UPDATED_USER = getUpdatedUser();
    public static final List<User> UPDATED_USER_LIST = getUpdatedUserList();
    //Bill
    public static final Bill NEW_BILL = getNewBill();
    public static final List<Bill> EXPECTED_BILL_LIST = getExpectedBillList();
    public static final Bill EXPECTED_BILL = getExpectedBill();
    public static final Bill UPDATED_BILL = getUpdatedBill();
    //Location
//    public static final Location NEW_LOCATION = getNewLocation();
//    public static final List<Location> EXPECTED_LOCATION_LIST = getExpectedLocationList();
//    public static final Location EXPECTED_LOCATION = getExpectedLocation();
//    public static final Location UPDATED_LOCATION = getUpdatedLocation();
//    //Hotel
//    public static final Hotel NEW_HOTEL = getNewHotel();
//    public static final List<Hotel> EXPECTED_HOTEL_LIST = getExpectedHotelList();
//    public static final Hotel EXPECTED_HOTEL = getExpectedHotel();
//    public static final List<Hotel> UPDATED_HOTEL_LIST = getUpdatedHotelList();
//    public static final Hotel UPDATED_HOTEL = getUpdatedHotel();
//    public static final List<Hotel> EXPECTED_HOTEL_LIST_BY_COUNTRY = getHotelByCountry();
//    public static final List<Hotel> EXPECTED_HOTEL_LIST_BY_CITY = getHotelByCity();

    private TestEntityBuilder() {

    }

    private static User getNewUser() {
        String firstName = "testFirstNameNew";
        String lastName = "testLastNameNew";
        String email = "tesNew@test.com";
        String login = "testLoginNew";
        String password = "testPasswordNew";
        String role = ROLE_USER;
        String userStatus = STATUS_ACTIVE;
        return new User(null, firstName, lastName, email, login, password, role, userStatus);
    }

    private static List<User> getNewUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= USERS_AMOUNT; i++) {
            long userId = i;
            String firstName = "testFirstNameNew" + i;
            String lastName = "testLastNameNew" + i;
            String email = "testNew" + i + "@gmail.com";
            String login = "testLoginNew" + i;
            String password = "testPasswordNew" + i;
            String role = ROLE_USER;
            String userStatus = STATUS_ACTIVE;
            userList.add(new User(userId, firstName, lastName, email, login, password, role, userStatus));
        }
        return userList;
    }

    private static List<User> getExpectedUserList() {
        List<User> expectedUserList = JsonManager.getUserList();
        return expectedUserList;
    }

    private static List<User> getUpdatedUserList() {
        List<User> userList = JsonManager.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            user.setFirstName("testFirstNameUpdated" + i);
        }
        return userList;
    }

    private static User getExpectedUser() {
        User expectedUser = getExpectedUserList().get(EXPECTED);
        return expectedUser;
    }

    private static User getUpdatedUser() {
        long id = getExpectedUser().getUserId();
        String firstName = "testFirstNameUpdated";
        String lastName = "testLastNameUpdated";
        String email = "tesUpdated@test.com";
        String login = "testLoginUpdated";
        String password = "testPasswordUpdated";
        String role = ROLE_USER;
        String userStatus = STATUS_ACTIVE;
        return new User(id, firstName, lastName, email, login, password, role, userStatus);

    }
    
    private static List<Language> getExpectedLanguageList() {
        List<Language> expectedLanguageList = JsonManager.getLanguageList();
        return expectedLanguageList;
    }

    private static Language getExpectedLanguage() {
        Language expectedLanguage = getExpectedLanguageList().get(EXPECTED);
        return expectedLanguage;
    }

    private static Bill getNewBill() {
        List<User> userList = JsonManager.getUserList();
        List<Room> roomList = JsonManager.getRoomList();
        User user = userList.get(EXPECTED);
        Set<Room> roomSet = new HashSet<>();
        for (int j = 1; j <= ROOMS_PER_BILL; j++) {
            Room room = roomList.get(j);
            roomSet.add(room);
        }
        Long bookingDate = new Date().getTime() - DAYS10 * 2;
        Long checkInDate = new Date().getTime() - DAYS10 * 2;
        Long checkOutDate = new Date().getTime() - DAYS10;
        Integer totalPersons = 6;
        Double paymentAmount = 450.5;
        String billStatus = STATUS_ACTIVE;
        return new Bill(null, bookingDate, user, totalPersons, checkInDate, checkOutDate,
                roomSet, paymentAmount, billStatus);
    }

    private static Bill getUpdatedBill() {
        List<User> userList = JsonManager.getUserList();
        List<Room> roomList = JsonManager.getRoomList();
        User user = userList.get(EXPECTED);
        Set<Room> roomSet = new HashSet<>();
        for (int j = 1; j <= ROOMS_PER_BILL + 1; j++) {
            Room room = roomList.get(j);
            roomSet.add(room);
        }
        Long bookingDate = new Date().getTime() - DAYS10 * 2;
        Long checkInDate = new Date().getTime() - DAYS10 * 2;
        Long checkOutDate = new Date().getTime() - DAYS10;
        Integer totalPersons = 8;
        Double paymentAmount = 450.5;
        String billStatus = STATUS_ACTIVE;
        return new Bill(null, bookingDate, user, totalPersons, checkInDate, checkOutDate,
                roomSet, paymentAmount, billStatus);
    }

    private static List<Bill> getExpectedBillList() {
        return JsonManager.getBillList();
    }

    private static Bill getExpectedBill() {
        return getExpectedBillList().get(EXPECTED);
    }

    private static List<Hotel> getExpectedHotelList() {
        return JsonManager.getHotelList();
    }

    private static Hotel getExpectedHotel() {
        return getExpectedHotelList().get(0);
    }

    private static List<Hotel> getNewHotelList() {
        List<Hotel> hotelList = new ArrayList<>();
        List<HotelTranslation> hotelTranslationList = JsonManager.getHotelTranslationList();
        List<Location> locationList = JsonManager.getLocationList();
        for (int i = 1; i <= HOTELS_AMOUNT; i++) {
            int locationId = Math.floorDiv(i - 1, HOTELS_PER_LOCATION);
            Location location = locationList.get(locationId);
            String hotelName = null;
            Map<Long, HotelTranslation> hotelTranslationMap = new HashMap<>();
            for (int j = 1; j <= LANGUAGES_AMOUNT; j++) {
                Integer hotelTranslationId = j + (i - 1) * LANGUAGES_AMOUNT;
                HotelTranslation hotelTranslation = hotelTranslationList.get(hotelTranslationId - 1);
                hotelTranslationMap.put(Integer.toUnsignedLong(j), hotelTranslation);
                if (j == 1) {
                    hotelName = hotelTranslation.getHotelName();
                }
            }
            String hotelStatus = STATUS_ACTIVE;
            Hotel hotel = new Hotel(null, hotelName, location, null, hotelTranslationMap, hotelStatus);
            hotelList.add(hotel);
        }
        return hotelList;
    }

}
