package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.dto.SearchOrder;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Hotel dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */
public interface IHotelDao extends IExtendedBaseDao<Hotel> {

    /**
     * Recives paginated hotels list by searchOrder. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param searchOrder filer for search hotels
     * @param page page number for pagination
     * @param perPage max list zize
     * @return an list of hotels
     * @throws DaoException
     */
    List<Hotel> getListByOrder(SearchOrder searchOrder, int page, int perPage) throws DaoException;

//    Hotel getByOrder(SearchOrder searchOrder) throws DaoException;

    /**
     * Recives number of hotels for this searchOrder
     * @param searchOrder filter for search hotels
     * @return number of hotels
     * @throws DaoException
     */
    Long getHotelsCount(SearchOrder searchOrder) throws DaoException;

}
