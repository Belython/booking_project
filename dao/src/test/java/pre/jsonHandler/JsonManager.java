package pre.jsonHandler;

import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.utils.SystemLogger;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pre.constants.ListType;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class JsonManager {

    private static Gson gson = new Gson();

    private JsonManager() {

    }

    public static List<User> getUserList() {
        List<User> userList = null;
        try {
            FileReader fileReader = new FileReader("src\\test\\java\\pre\\jsons\\users.json");
            JsonReader jsonReader = new JsonReader(fileReader);
            Type userType = ListType.USER;
            userList = gson.fromJson(jsonReader, userType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return userList;
    }

    public static List<Location> getLocationList() {
        List<Location> locationList = null;
        try {
            FileReader fileReader = new FileReader("src\\test\\java\\pre\\jsons\\locations.json");
            JsonReader jsonReader = new JsonReader(fileReader);
            Type locationType = ListType.LOCATION;
            locationList = gson.fromJson(jsonReader, locationType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return locationList;
    }

    public static List<Hotel> getHotelList() {
        List<Hotel> hotelList = null;
        try {
            FileReader fileReader = new FileReader("src\\test\\java\\pre\\jsons\\hotels.json");
            JsonReader jsonReader = new JsonReader(fileReader);
            Type hotelType = ListType.HOTEL;
            hotelList = gson.fromJson(jsonReader, hotelType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return hotelList;
    }



}
