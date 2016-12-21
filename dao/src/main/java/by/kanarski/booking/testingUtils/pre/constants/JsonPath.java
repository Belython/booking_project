package by.kanarski.booking.testingUtils.pre.constants;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class JsonPath {

    public static final String USERS = getPath() + "users.json";
    public static final String LANGUAGES = getPath() + "languages.json";
    public static final String LOCATION_TRANSLATIONS = getPath() + "locationTranslations.json";
    public static final String LOCATIONS = getPath() + "locations.json";
    public static final String HOTEL_TRANSLATIONS = getPath() + "hotelTranslations.json";
    public static final String HOTELS = getPath() + "hotels.json";
    public static final String FACILITY_TRANSLATIONS = getPath() + "faciltyTranslations.json";
    public static final String FACILITYS = getPath() + "facilitys.json";
    public static final String ROOM_TYPE_TRANSLATIONS = getPath() + "roomTypeTranslations.json";
    public static final String ROOM_TYPES = getPath() + "roomTypes.json";
    public static final String ROOMS = getPath() + "rooms.json";
    public static final String BILLS = getPath() + "bills.json";

    private static String getPath() {
        String path = null;
        try {
            path = Paths.get(".")
                    .resolve("../jsons/")
                    .toFile()
                    .getCanonicalPath();
        } catch (IOException e) {

        }
        path = "E:\\JD2learn\\projects\\booking_project\\jsons\\";
        return path;
    }

}
