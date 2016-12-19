package pre.jsonHandler;

import by.kanarski.booking.dao.interfaces.*;
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
import by.kanarski.booking.exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pre.constants.JsonPath;
import pre.constants.ListType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TestDatabaseFiller {

    @Autowired
    private TestEntityGenerator testEntityGenerator;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ILanguageDao languageDao;

    @Autowired
    private ILocationTranslationDao locationTranslationDao;

    @Autowired
    private ILocationDao locationDao;

    @Autowired
    private IHotelTranslationDao hotelTranslationDao;

    @Autowired
    private IHotelDao hotelDao;

    @Autowired
    private IFacilityTranslationDao facilityTranslationDao;

    @Autowired
    private IFacilityDao facilityDao;

    @Autowired
    private IRoomTypeTranslationDao roomTypeTranslationDao;

    @Autowired
    private IRoomTypeDao roomTypeDao;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IBillDao billDao;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setPrettyPrinting();


    public void generateJsons() throws DaoException, IOException {
        generateUsersJson();
        generateLanguagesJson();
        generateLocationTranslationsJson();
        generateLocationsJson();
        generateHotelTranslationsJson();
        generateHotelsJson();
        generateFacilityTranslationsJson();
        generateFacilitysJson();
        generateRoomTypeTranslationsJson();
        generateRoomTypesJson();
    }

    public void addToDatabase() throws DaoException {
        userDao.addList(JsonManager.getUserList());
        flushAndClearSession();
        languageDao.addList(JsonManager.getLanguageList());
        flushAndClearSession();
        locationTranslationDao.addList(JsonManager.getLocationTranslationList());
        flushAndClearSession();
        locationDao.addList(JsonManager.getLocationList());
        flushAndClearSession();
        hotelTranslationDao.addList(JsonManager.getHotelTranslationList());
        flushAndClearSession();
        hotelDao.addList(JsonManager.getHotelList());
        flushAndClearSession();
        facilityTranslationDao.addList(JsonManager.getFacilityTranslationList());
        flushAndClearSession();
        facilityDao.addList(JsonManager.getFacilityList());
        flushAndClearSession();
        roomTypeTranslationDao.addList(JsonManager.getRoomTypeTranslationList());
        flushAndClearSession();
        roomTypeDao.addList(JsonManager.getRoomTypeList());
        flushAndClearSession();
    }

//    private void getAdded() throws DaoException {
//        recivedUserList = userDao.getAll();
////        recivedLocationList = LocationDao.getInstance().getAll();
////        recivedHotelList = HotelDao.getInstance().getAll();
//    }

    private void generateUsersJson() throws IOException {
        List<User> generatedUserList = testEntityGenerator.generateUsers();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.USERS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.USER;
        gson.toJson(generatedUserList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateLanguagesJson() throws IOException {
        List<Language> generatedLanguageList = testEntityGenerator.generateLanguages();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.LANGUAGES);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.LANGUAGE;
        gson.toJson(generatedLanguageList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateLocationTranslationsJson() throws IOException {
        List<LocationTranslation> generatedLocationTranslationList = testEntityGenerator.generateLocationTranslations();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.LOCATION_TRANSLATIONS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.LOCATION_TRANSLATION;
        gson.toJson(generatedLocationTranslationList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateLocationsJson() throws IOException {
        List<Location> generatedLocationList = testEntityGenerator.generateLocations();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.LOCATIONS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.LOCATION;
        gson.toJson(generatedLocationList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateHotelTranslationsJson() throws IOException {
        List<HotelTranslation> generatedHotelTranslationList = testEntityGenerator.generateHotelTranslations();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.HOTEL_TRANSLATIONS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.HOTEL_TRANSLATION;
        gson.toJson(generatedHotelTranslationList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateHotelsJson() throws IOException {
        List<Hotel> generatedHotelList = testEntityGenerator.generateHotels();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.HOTELS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.HOTEL;
        gson.toJson(generatedHotelList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateFacilityTranslationsJson() throws IOException {
        List<FacilityTranslation> generatedFacilityTranslationList = testEntityGenerator.generateFacilityTranslations();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.FACILITY_TRANSLATIONS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.FACILITY_TRANSLATION;
        gson.toJson(generatedFacilityTranslationList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateFacilitysJson() throws IOException {
        List<Facility> generatedFacilityList = testEntityGenerator.generateFacilitys();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.FACILITYS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.FACILITY;
        gson.toJson(generatedFacilityList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateRoomTypeTranslationsJson() throws IOException {
        List<RoomTypeTranslation> generatedRoomTypeTranslationList = testEntityGenerator.generateRoomTypeTranslations();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.ROOM_TYPE_TRANSLATIONS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.ROOM_TYPE_TRANSLATION;
        gson.toJson(generatedRoomTypeTranslationList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateRoomTypesJson() throws IOException {
        List<RoomType> generatedRoomTypeList = testEntityGenerator.generateRoomTypes();
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.ROOM_TYPES);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.ROOM_TYPE;
        gson.toJson(generatedRoomTypeList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateRoomsJson() throws IOException {
//        List<Room> generatedRoomList = testEntityGenerator.generateRooms();
        List<Room> generatedRoomList = null;
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.ROOMS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.ROOM;
        gson.toJson(generatedRoomList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void generateBillsJson() throws IOException {
//        List<Bill> generatedBillList = testEntityGenerator.generateBills();
        List<Bill> generatedBillList = null;
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.BILLS);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.BILL;
        gson.toJson(generatedBillList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }

    private void flushAndClearSession() {
        if (session == null) {
            session = sessionFactory.getCurrentSession();
        }
        session.flush();
        session.clear();
    }

}
