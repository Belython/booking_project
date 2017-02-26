package by.kanarski.booking.utils;

/**
 * Contains SQL queries for retrieving specific entitiy fields,
 * marked with <code>@Formula</code> annotation
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class Formulas {

    public static final String COUNTRY_FORMULA = "(SELECT LT.COUNTRY FROM LOCATION_TRANSLATIONS LT " +
            "WHERE LT.LOCATION_ID = LOCATION_ID " +
            "AND LT.LANGUAGE_ID = " +
            "(SELECT L.LANGUAGE_ID FROM LANGUAGES L WHERE L.NAME = 'EN'))";

    public static final String CITY_FORMULA = "(SELECT LT.CITY FROM LOCATION_TRANSLATIONS LT " +
            "WHERE LT.LOCATION_ID = LOCATION_ID " +
            "AND LT.LANGUAGE_ID = " +
            "(SELECT L.LANGUAGE_ID FROM LANGUAGES L WHERE L.NAME = 'EN'))";

    public static final String DETAIL_NAME_FORMULA = "(SELECT FT.DETAIL_NAME FROM DETAIL_TRANSLATIONS FT " +
            "WHERE FT.DETAIL_ID = DETAIL_ID " +
            "AND FT.LANGUAGE_ID = " +
            "(SELECT L.LANGUAGE_ID FROM LANGUAGES L WHERE L.NAME = 'EN'))";

    public static final String ROOM_TYPE_NAME_FORMULA = "(SELECT RTT.ROOM_TYPE_NAME FROM ROOM_TYPE_TRANSLATIONS RTT " +
            "WHERE RTT.ROOM_TYPE_ID = ROOM_TYPE_ID " +
            "AND RTT.LANGUAGE_ID = " +
            "(SELECT L.LANGUAGE_ID FROM LANGUAGES L WHERE L.NAME = 'EN'))";

    public static final String HOTEL_NAME_FORMULA = "(SELECT HT.HOTEL_NAME FROM HOTEL_TRANSLATIONS HT " +
            "WHERE HT.HOTEL_ID = HOTEL_ID " +
            "AND HT.LANGUAGE_ID = " +
            "(SELECT L.LANGUAGE_ID FROM LANGUAGES L WHERE L.NAME = 'EN'))";
}
