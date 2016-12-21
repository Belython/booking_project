package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Hotel service interface
 * @author Dzmitry Kanarski
 * @version 1.0
 */
public interface IHotelService extends IExtendedBaseService<Hotel, HotelDto> {

    /**
     * Recives list hotel DTOs by hotel name
     * @param hotelDto hotel DTO as filter
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of hotel DTOs
     * @throws ServiceException
     */
    List<HotelDto> getByHotelName(HotelDto hotelDto, int page, int perPage) throws ServiceException;

    /**
     * Recives list hotel DTOs by country
     * @param hotelDto hotel DTO as filter
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of hotel DTOs
     * @throws ServiceException
     */
    List<HotelDto> getByCountry(HotelDto hotelDto, int page, int perPage) throws ServiceException;

    /**
     * Recives list hotel DTOs by city
     * @param hotelDto hotel DTO as filter
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of hotel DTOs
     * @throws ServiceException
     */
    List<HotelDto> getByCity(HotelDto hotelDto, int page, int perPage) throws ServiceException;

    /**
     * Recives list hotel DTOs by destination DTO, which can contain country, city, hotel name or all together
     * @param destinationDto detination DTO, wraps hotel
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of hotel DTOs
     * @throws ServiceException
     */
    List<HotelDto> getByDestination(DestinationDto destinationDto, int page, int perPage) throws ServiceException;

    /**
     * Recives one hotel by country, city and hotel name
     * @param description hotel DTO, that must be full stocked
     * @return required hotel DTO
     * @throws ServiceException
     */
    HotelDto getByDescription(HotelDto description) throws ServiceException;

}
