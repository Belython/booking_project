package pre.constants;

import java.io.File;
import java.io.IOException;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class JsonPath {


    public static final String USERS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\users.json";
    public static final String LANGUAGES = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\languages.json";
    public static final String LOCATION_TRANSLATIONS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\locationTranslations.json";
    public static final String LOCATIONS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\locations.json";
    public static final String HOTEL_TRANSLATIONS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\hotelTranslations.json";
    public static final String HOTELS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\hotels.json";
    public static final String FACILITY_TRANSLATIONS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\faciltyTranslations.json";
    public static final String FACILITYS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\facilitys.json";
    public static final String ROOM_TYPE_TRANSLATIONS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\roomTypeTranslations.json";
    public static final String ROOM_TYPES = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\roomTypes.json";
    public static final String ROOMS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\rooms.json";
    public static final String BILLS = getPath() + "\\dao\\src\\test\\java\\pre\\jsons\\bills.json";

    private static String getPath() {
        String path = null;
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException e) {

        }
        return path;
    }

}
