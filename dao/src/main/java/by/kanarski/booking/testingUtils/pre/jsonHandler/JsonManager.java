package by.kanarski.booking.testingUtils.pre.jsonHandler;

import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Language;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.utils.SystemLogger;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import by.kanarski.booking.testingUtils.pre.constants.JsonPath;
import by.kanarski.booking.testingUtils.pre.constants.ListType;

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
            FileReader fileReader = new FileReader(JsonPath.USERS);
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

    public static List<Language> getLanguageList() {
        List<Language> languageList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.LANGUAGES);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type languageType = ListType.LANGUAGE;
            languageList = gson.fromJson(jsonReader, languageType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return languageList;
    }

    public static List<LocationTranslation> getLocationTranslationList() {
        List<LocationTranslation> locationTranslationList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.LOCATION_TRANSLATIONS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type locationTranslationType = ListType.LOCATION_TRANSLATION;
            locationTranslationList = gson.fromJson(jsonReader, locationTranslationType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return locationTranslationList;
    }

    public static List<Location> getLocationList() {
        List<Location> locationList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.LOCATIONS);
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

    public static List<HotelTranslation> getHotelTranslationList() {
        List<HotelTranslation> hotelTranslationList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.HOTEL_TRANSLATIONS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type hotelTranslationType = ListType.HOTEL_TRANSLATION;
            hotelTranslationList = gson.fromJson(jsonReader, hotelTranslationType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return hotelTranslationList;
    }

    public static List<Hotel> getHotelList() {
        List<Hotel> hotelList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.HOTELS);
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

    public static List<FacilityTranslation> getFacilityTranslationList() {
        List<FacilityTranslation> facilityTranslationList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.FACILITY_TRANSLATIONS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type facilityTranslationType = ListType.FACILITY_TRANSLATION;
            facilityTranslationList = gson.fromJson(jsonReader, facilityTranslationType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return facilityTranslationList;
    }

    public static List<Facility> getFacilityList() {
        List<Facility> facilityList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.FACILITYS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type facilityType = ListType.FACILITY;
            facilityList = gson.fromJson(jsonReader, facilityType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return facilityList;
    }

    public static List<RoomTypeTranslation> getRoomTypeTranslationList() {
        List<RoomTypeTranslation> roomTypeTranslationList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.ROOM_TYPE_TRANSLATIONS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type roomTypeTranslationType = ListType.ROOM_TYPE_TRANSLATION;
            roomTypeTranslationList = gson.fromJson(jsonReader, roomTypeTranslationType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return roomTypeTranslationList;
    }

    public static List<RoomType> getRoomTypeList() {
        List<RoomType> roomTypeList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.ROOM_TYPES);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type roomTypeType = ListType.ROOM_TYPE;
            roomTypeList = gson.fromJson(jsonReader, roomTypeType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return roomTypeList;
    }

    public static List<Room> getRoomList() {
        List<Room> roomList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.ROOMS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type roomType = ListType.ROOM;
            roomList = gson.fromJson(jsonReader, roomType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return roomList;
    }

    public static List<Bill> getBillList() {
        List<Bill> billList = null;
        try {
            FileReader fileReader = new FileReader(JsonPath.BILLS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Type billType = ListType.BILL;
            billList = gson.fromJson(jsonReader, billType);
            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(JsonManager.class, e.getMessage(), e);
        }
        return billList;
    }

}
