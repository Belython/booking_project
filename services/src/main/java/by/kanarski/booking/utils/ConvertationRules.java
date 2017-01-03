package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.LocalisationException;
import org.springframework.core.convert.converter.*;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ConvertationRules {


    private RoomDto toRoomDto(Room room) throws LocalisationException {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = toStateDto(room.getRoomStatus());
        return new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, roomStatus);
    }

    org.springframework.core.convert.converter.Converter
    public class RoomDtoConverter implements Conver

}
