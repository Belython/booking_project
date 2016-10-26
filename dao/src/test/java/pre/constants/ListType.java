package pre.constants;

import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.entities.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ListType {

    public static final Type USER = new TypeToken<List<User>>(){}.getType();
    public static final Type LOCATION = new TypeToken<List<Location>>(){}.getType();
    public static final Type HOTEL = new TypeToken<List<Hotel>>(){}.getType();
    public static final Type ROOM_TYPE = new TypeToken<List<RoomType>>(){}.getType();

    private ListType() {

    }

}
