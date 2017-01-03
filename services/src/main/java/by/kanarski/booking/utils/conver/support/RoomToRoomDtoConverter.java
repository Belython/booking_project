package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomToRoomDtoConverter implements Converter<Room, RoomDto> {

    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public RoomDto convert(Room room) {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = conversionService.convert(roomHotel, HotelDto.class);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = conversionService.convert(roomType, RoomTypeDto.class);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = conversionService.convert(room.getRoomStatus(), String.class);
        return new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, roomStatus);
    }
}
