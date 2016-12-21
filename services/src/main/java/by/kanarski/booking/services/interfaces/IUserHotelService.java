package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * User hotel service interface
 * @author Dzmitry Kanarski
 * @version 1.0
 */
public interface IUserHotelService extends IExtendedBaseService<Hotel, UserHotelDto> {

    /**
     * Recives paginated user hotel DTOs list by order DTO. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param orderDto an order, that contains check-in date, check-out date and another parameters
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of user hotel DTOs
     * @throws ServiceException
     */
    List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException;

    /**
     * Recives user hotel DTO by order DTO. Order must be completely filled
     * @param orderDto an order, that contains check-in date, check-out date and another parameters
     * @return proper user hotel DTO
     * @throws ServiceException
     */
    UserHotelDto getByOrder(OrderDto orderDto) throws ServiceException;

    /**
     * Recives number of hotels for this order DTO
     * @param orderDto an order, that contains check-in date, check-out date and another parameters
     * @return number of hotels
     * @throws ServiceException
     */
    Long getHotelsCount(OrderDto orderDto) throws ServiceException;

}
