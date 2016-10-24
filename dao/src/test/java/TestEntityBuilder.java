import by.kanarski.booking.constants.FieldValue;
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
        User newUser = EntityBuilder.buildUser("testFirstNameNew", "testLastNameNew", "tesNew@test.com",
                "testLoginNew", "testPasswordNew", FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return newUser;
    }

    public static User getUpdatedUser(User expectedUser) {
        User updatedUser = EntityBuilder.buildUser(expectedUser.getUserId(), "testFirstNameUpdated",
                "testLastNameUpdated", "tesUpdated@test.com", "testLoginUpdated", "testPasswordUpdated",
                FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return updatedUser;
    }

    public static List<User> getExpectedUserList(List<User> expectedUserList) {
        expectedUserList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long testId = i;
            String testFirstName = "testFirstName" + i;
            String testLastName = "testLastName" + i;
            String testEmail = "test" + i + "@gmail.com";
            String testLogin = "testLogin" + i;
            String testPassword = "testPassword" + i;
            String role = FieldValue.ROLE_CLIENT;
            String status = FieldValue.STATUS_ACTIVE;
            User user = EntityBuilder.buildUser(testId, testFirstName, testLastName, testEmail, testLogin,
                    testPassword, role, status);
            expectedUserList.add(user);
        }
        return expectedUserList;
    }

    public static Location getNewLocation() {
        Location newLocation = EntityBuilder.buildLocation("testCountry", "testCity", FieldValue.STATUS_ACTIVE);
        return newLocation;
    }

    public static Location getUpdatedLocation(Location expectedLocation) {
        Location updatedLocation = EntityBuilder.buildLocation(expectedLocation.getLocationId(), "testFirstNameUpdated",
                "testLastNameUpdated", "tesUpdated@test.com", "testLoginUpdated", "testPasswordUpdated",
                FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return updatedLocation;
    }

    public static List<Location> getExpectedLocationList(List<Location> expectedLocationList) {
        expectedLocationList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long testId = i;
            String testFirstName = "testFirstName" + i;
            String testLastName = "testLastName" + i;
            String testEmail = "test" + i + "@gmail.com";
            String testLogin = "testLogin" + i;
            String testPassword = "testPassword" + i;
            String role = FieldValue.ROLE_CLIENT;
            String status = FieldValue.STATUS_ACTIVE;
            Location user = EntityBuilder.buildLocation(testId, testFirstName, testLastName, testEmail, testLogin,
                    testPassword, role, status);
            expectedLocationList.add(user);
        }
        return expectedLocationList;
    }



}
