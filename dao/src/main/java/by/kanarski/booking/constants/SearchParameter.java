package by.kanarski.booking.constants;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SearchParameter {

    public static final String LANGUAGE_NAME = "language.name";

    //User parameters

    public static final String USERID = "userId";
    public static final String USER_USERIDID = "user.userId";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL= "email";
    public static final String FIRSTNAME = "firstName";

    //Hotel parameters

    public static final String HOTELID = "hotelId";
    public static final String HOTELNAME = "hotelName";
    public static final String LOCATION_COUNTRY = "location.country";
    public static final String LOCATION_CITY = "location.city";

    //Location parameters

    public static final String COUNTRY = "country";
    public static final String CITY = "city";

    //Room parameters

    public static final String HOTEL_HOTELID = "hotel.hotelId";
    public static final String HOTEL_LOCATION_COUNTRY = "location.country";
    public static final String HOTEL_LOCATION_CITY = "location.city";
    public static final String ROOMTYPE_ROOMTYPENAME = "roomType.roomTypeName";
    public static final String ROOMTYPE_MAXPERSONS = "roomType.maxPersons";
    public static final String ROOMTYPE_PRICEPERNIGHT= "roomType.pricePerNight";
    public static final String BILLSET_CHECKINDATE = "billSet.checkInDate";
    public static final String BILLSET_CHECKOUTDATE = "billSet.checkOutDate";

    //RoomType parameters

    public static final String ROOMTYPENAME = "roomTypeName";
    public static final String FACILITYSET_FACILITYNAME = "facilitySet.facilityName";

    //Facility parameters
    public static final String FACILITYNAME = "facilityName";

    //Bill parameters
    public static final String BILLSTATUS = "billStatus";

}
