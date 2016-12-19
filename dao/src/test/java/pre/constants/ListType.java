package pre.constants;

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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ListType {

    public static final Type USER = new TypeToken<List<User>>(){}.getType();
    public static final Type LANGUAGE = new TypeToken<List<Language>>(){}.getType();
    public static final Type LOCATION_TRANSLATION = new TypeToken<List<LocationTranslation>>(){}.getType();
    public static final Type LOCATION = new TypeToken<List<Location>>(){}.getType();
    public static final Type HOTEL_TRANSLATION = new TypeToken<List<HotelTranslation>>(){}.getType();
    public static final Type HOTEL = new TypeToken<List<Hotel>>(){}.getType();
    public static final Type FACILITY_TRANSLATION = new TypeToken<List<FacilityTranslation>>(){}.getType();
    public static final Type FACILITY = new TypeToken<List<Facility>>(){}.getType();
    public static final Type ROOM_TYPE_TRANSLATION = new TypeToken<List<RoomTypeTranslation>>(){}.getType();
    public static final Type ROOM_TYPE = new TypeToken<List<RoomType>>(){}.getType();
    public static final Type ROOM = new TypeToken<List<Room>>(){}.getType();
    public static final Type BILL = new TypeToken<List<Bill>>(){}.getType();

    private ListType() {

    }

}
