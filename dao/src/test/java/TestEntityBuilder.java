import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.utils.EntityBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 24.10.2016.
 */
public class TestEntityBuilder {
    
    private TestEntityBuilder() {
        
    }

    public static User getNewUser() {
        String firstName = "testFirstNameNew";
        String lastName = "testLastNameNew";
        String email = "tesNew@test.com";
        String login = "testLoginNew";
        String password = "testPasswordNew";
        String role = FieldValue.ROLE_CLIENT;
        String status = FieldValue.STATUS_ACTIVE;
        User newUser = EntityBuilder.buildUser(firstName, lastName, email, login, password, role, status);
        return newUser;
    }

    public static User getUpdatedUser(User expectedUser) {
        long id = expectedUser.getUserId();
        String firstName = "testFirstNameUpdated";
        String lastName = "testLastNameUpdated";
        String email = "tesUpdated@test.com";
        String login = "testLoginUpdated";
        String password = "testPasswordUpdated";
        String role = FieldValue.ROLE_CLIENT;
        String status = FieldValue.STATUS_ACTIVE;
        User updatedUser = EntityBuilder.buildUser(id, firstName, lastName, email, login, password, role, status);
        return updatedUser;
    }

    public static List<User> getExpectedUserList() {
        List<User> expectedUserList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long id = i;
            String firstName = "testFirstName" + i;
            String lastName = "testLastName" + i;
            String email = "test" + i + "@gmail.com";
            String login = "testLogin" + i;
            String password = "testPassword" + i;
            String role = FieldValue.ROLE_CLIENT;
            String status = FieldValue.STATUS_ACTIVE;
            User user = EntityBuilder.buildUser(id, firstName, lastName, email, login,
                    password, role, status);
            expectedUserList.add(user);
        }
        return expectedUserList;
    }

    public static Location getNewLocation() {
        String country = "testCountryNew";
        String city = "testCityNew";
        String status = FieldValue.STATUS_ACTIVE;
        Location newLocation = EntityBuilder.buildLocation(country, city, status);
        return newLocation;
    }

    public static Location getUpdatedLocation(Location expectedLocation) {
        long id = expectedLocation.getLocationId();
        String country = "testCountryUpdated";
        String city = "testCityUpdated";
        String status = FieldValue.STATUS_ACTIVE;
        Location updatedLocation = EntityBuilder.buildLocation(id, country, city, status);
        return updatedLocation;
    }

    public static List<Location> getExpectedLocationList() {
        List<Location> expectedLocationList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long id = i;
            String country = "testCountry" + i;
            String city = "testCity" + i;
            String status = FieldValue.STATUS_ACTIVE;
            Location user = EntityBuilder.buildLocation(id, country, city, status);
            expectedLocationList.add(user);
        }
        return expectedLocationList;
    }

    public static Hotel getNewHotel() {
        Location testLocation = getExpectedLocationList().get(1);
        String hotelName = "testHotelNameNew";
        String status = FieldValue.STATUS_ACTIVE;
        Hotel newHotel = EntityBuilder.buildHotel(testLocation, hotelName, status);
        return newHotel;
    }

    public static Hotel getUpdatedHotel(Hotel expectedHotel) {
        Location testLocation = getExpectedLocationList().get(0);
        String hotelName = "testHotelNameNew";
        String status = FieldValue.STATUS_ACTIVE;
        Hotel updatedHotel = EntityBuilder.buildHotel(expectedHotel.getHotelId(), "testCountryUpdated",
                "testCityUpdated", FieldValue.STATUS_ACTIVE);
        return updatedHotel;
    }

    public static List<Hotel> getExpectedHotelList() {
        List<Hotel> expectedHotelList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long testId = i;
            String testCountry = "testCountry" + i;
            String testCity = "testCity" + i;
            String status = FieldValue.STATUS_ACTIVE;
            Hotel user = EntityBuilder.buildHotel(testId, testCountry, testCity, status);
            expectedHotelList.add(user);
        }
        return expectedHotelList;
    }


}
