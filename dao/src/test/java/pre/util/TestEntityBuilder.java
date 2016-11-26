package pre.util;

import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.EntityBuilder;
import pre.jsonHandler.JsonManager;

import java.util.ArrayList;
import java.util.List;

import static by.kanarski.booking.constants.FieldValue.ROLE_CLIENT;

public class TestEntityBuilder {

//    private static int EXPECTED = 0;
//    private static int UPDATED = 0;
//    private static int EXPECTED_START = 1;
//    private static int EXPECTED_AMOUNT = 30;
//    private static int UPDATED_START = EXPECTED_START;
//    private static int UPDATED_AMOUNT = EXPECTED_AMOUNT;
//    private static int NEW_SUBENTITY = 1;
//
//    //User
//    public static final User NEW_USER = getNewUser();
//    public static final List<User> EXPECTED_USER_LIST = getExpectedUserList();
//    public static final User EXPECTED_USER = getExpectedUser();
//    public static final User UPDATED_USER = getUpdatedUser();
//    //Location
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
////    public static final List<Hotel> EXPECTED_HOTEL_LIST_BY_COUNTRY = getHotelByCountry();
////    public static final List<Hotel> EXPECTED_HOTEL_LIST_BY_CITY = getHotelByCity();
//
//    private TestEntityBuilder() {
//
//    }
//
//    private static User getNewUser() throws DaoException {
//        String firstName = "testFirstNameNew";
//        String lastName = "testLastNameNew";
//        String email = "tesNew@test.com";
//        String login = "testLoginNew";
//        String password = "testPasswordNew";
//        String role = ROLE_CLIENT;
//        User newUser = EntityBuilder.buildUser(firstName, lastName, email, login, password, role);
//        return newUser;
//    }
//
//    private static List<User> getExpectedUserList() {
//        List<User> expectedUserList = JsonManager.getUserList();
//        return expectedUserList;
//    }
//
//    private static User getExpectedUser() {
//        User expectedUser = getExpectedUserList().get(EXPECTED);
//        return expectedUser;
//    }
//
//    private static User getUpdatedUser() throws DaoException {
//        long id = getExpectedUser().getUserId();
//        String firstName = "testFirstNameUpdated";
//        String lastName = "testLastNameUpdated";
//        String email = "tesUpdated@test.com";
//        String login = "testLoginUpdated";
//        String password = "testPasswordUpdated";
//        String role = ROLE_CLIENT;
//        User updatedUser = EntityBuilder.buildUser(id, firstName, lastName, email, login, password, role);
//        return updatedUser;
//    }
//
//
//    private static Location getNewLocation() throws DaoException {
//        String country = "testCountryNew";
//        String city = "testCityNew";
//        Location newLocation = EntityBuilder.buildLocation(country, city);
//        return newLocation;
//    }
//
//    private static List<Location> getExpectedLocationList() {
//        List<Location> expectedLocationList = JsonManager.getLocationList();
//        return expectedLocationList;
//    }
//
//    private static Location getExpectedLocation() {
//        Location expectedLocation = getExpectedLocationList().get(EXPECTED);
//        return expectedLocation;
//    }
//
//    private static Location getUpdatedLocation() throws DaoException {
//        long id = getExpectedLocation().getLocationId();
//        String country = "testCountryUpdated";
//        String city = "testCityUpdated";
//        Location updatedLocation = EntityBuilder.buildLocation(id, country, city);
//        return updatedLocation;
//    }
//
//
//    private static Hotel getNewHotel() throws DaoException {
//        Location hotelLocation = getExpectedLocationList().get(NEW_SUBENTITY);
//        String hotelName = "testHotelNameNew";
//        Hotel newHotel = EntityBuilder.buildHotel(hotelLocation, hotelName);
//        return newHotel;
//    }
//
//    private static List<Hotel> getExpectedHotelList() {
//        List<Hotel> expectedHotelList = JsonManager.getHotelList();
//        return expectedHotelList;
//    }
//
//    private static Hotel getExpectedHotel() {
//        Hotel expectedHotel = getExpectedHotelList().get(EXPECTED);
//        return expectedHotel;
//    }
//
//    private static List<Hotel> getUpdatedHotelList() throws DaoException {
//        List<Hotel> updatedHotelList = new ArrayList<>();
//        for (int i = UPDATED_START; i <= UPDATED_AMOUNT; i++) {
//            long testId = i;
//            Location hotelLocation = getExpectedLocationList().get(i - 1);
//            String hotelName = "testHotelNameUpdated" + i;
//            Hotel user = EntityBuilder.buildHotel(testId, hotelLocation, hotelName);
//            updatedHotelList.add(user);
//        }
//        return updatedHotelList;
//    }
//
//    private static Hotel getUpdatedHotel() throws DaoException {
//        Hotel updatedHotel = getUpdatedHotelList().get(UPDATED);
//        return updatedHotel;
//    }

//    private static List<Hotel> getHotelByCountry() {
//        String country = EXPECTED_HOTEL.getLocation().getCountry();
//        List<Hotel> constrainedHotelList = new ArrayList<>();
//        for (Hotel hotel : EXPECTED_HOTEL_LIST) {
//            String compCountry = hotel.getLocation().getCountry();
//            if (country.equals(compCountry)) {
//                constrainedHotelList.add(hotel);
//            }
//        }
//        return constrainedHotelList;
//    }
//
//
//    private static List<Hotel> getHotelByCity() {
//        String city = EXPECTED_HOTEL.getLocation().getCity();
//        List<Hotel> constrainedHotelList = new ArrayList<>();
//        for (Hotel hotel : EXPECTED_HOTEL_LIST) {
//            String compCity = hotel.getLocation().getCity();
//            if (city.equals(compCity)) {
//                constrainedHotelList.add(hotel);
//            }
//        }
//        return constrainedHotelList;
//    }

}
