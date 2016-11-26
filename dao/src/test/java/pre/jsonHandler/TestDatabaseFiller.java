package pre.jsonHandler;

import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.LocationDao;
import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
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

public class TestDatabaseFiller {

    private static TestDatabaseFiller instance;

    private TestEntityGenerator testEntityGenerator = TestEntityGenerator.getInstance();
    private GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setPrettyPrinting();

    private List<User> generatedUserList;
    private List<Location> generatedLocationList;
    private List<Hotel> generatedHotelList;
    private List<RoomType> generatedRoomTypeList;
    private List<Room> generatedRoomList;
    private List<Bill> generatedBillList;


    private List<User> recivedUserList;
    private List<Location> recivedLocationList;
    private List<Hotel> recivedHotelList;
    private List<RoomType> recivedRoomTypeList;
    private List<Room> recivedRoomList;
    private List<Bill> recivedBillList;

    private TestDatabaseFiller() {

    }

    public static TestDatabaseFiller getInstance() {
        if (instance == null) {
            instance = new TestDatabaseFiller();
        }
        return instance;
    }

    public void execute() throws DaoException, IOException {
        generate();
        addToDatabase();
        generateJsons();
    }

    private void generate() throws DaoException {
        generatedUserList = testEntityGenerator.generateUsers();
        generatedLocationList = testEntityGenerator.generateLocations();
        generatedHotelList = testEntityGenerator.generateHotels(generatedLocationList);
    }

    private void addToDatabase() throws DaoException {
        UserDao.getInstance().addList(generatedUserList);
        LocationDao.getInstance().addList(generatedLocationList);
        HotelDao.getInstance().addList(generatedHotelList);
    }
//
//    private void getAdded() throws DaoException {
//        recivedUserList = UserDao.getInstance().getAll();
//        recivedLocationList = LocationDao.getInstance().getAll();
//        recivedHotelList = HotelDao.getInstance().getAll();
//    }

    private void generateJsons() throws DaoException, IOException {
        generateUsersJson();
        generateLocationsJson();
        generateHotelsJson();
        generateRoomTypesJson();
    }

    private void generateUsersJson() throws IOException {
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

    private void generateLocationsJson() throws IOException {
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

    private void generateHotelsJson() throws IOException {
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

    private void generateRoomTypesJson() throws IOException {
        Gson gson = gsonBuilder.create();
        File jsonFile = new File(JsonPath.ROOM_TYPES);
        jsonFile.createNewFile();
        FileWriter fileWriter = new FileWriter(jsonFile);
        JsonWriter jsonWriter = gson.newJsonWriter(fileWriter);
        Type type = ListType.HOTEL;
        gson.toJson(generatedRoomTypeList, type, jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
        fileWriter.close();
    }



}
