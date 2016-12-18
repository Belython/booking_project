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

    List<Hotel> getListByOrder(SearchOrder searchOrder, int page, int perPage) throws DaoException;

    Long getHotelsCount(SearchOrder searchOrder) throws DaoException;


    }
