package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Room service interface
 * @author Dzmitry Kanarski
 * @version 1.0
 */
public interface IRoomService extends IExtendedBaseService<Room, RoomDto> {

    /**
     * Recives list of room DTOs by hotel id. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param hotelId hotel id
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of room DTO with needed hotel id
     * @throws ServiceException
     */
    List<RoomDto> getByHotelId(Long hotelId, int page, int perPage) throws ServiceException;

}
