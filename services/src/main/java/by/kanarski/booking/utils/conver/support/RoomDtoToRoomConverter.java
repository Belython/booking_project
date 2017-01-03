package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RoomDtoToRoomConverter implements Converter<RoomDto, Room> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public Room convert(RoomDto roomDto) {
        Long roomId = roomDto.getRoomId();
        Hotel hotel = conversionService.convert(roomDto.getHotel(), Hotel.class);
        RoomType roomType = conversionService.convert(roomDto.getRoomType(), RoomType.class);
        Integer roomNumber = roomDto.getRoomNumber();
        State roomStatus = conversionService.convert(roomDto.getRoomStatus(), State.class);
        return entityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, roomStatus);
    }
}
