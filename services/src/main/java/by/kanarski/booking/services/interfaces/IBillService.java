package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.forms.BookRoomsForm;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Bill service interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IExtendedBaseService
 */
public interface IBillService extends IExtendedBaseService<Bill, BillDto> {

    /**
     * Recives list of bills DTOs by user id. List limited by (page * perPage) below
     * and (page * perPage + perPage) above
     * @param userId user id for search
     * @param page page number for pagination
     * @param perPage max list zize
     * @return list of bill DTOs
     * @throws ServiceException
     */
    List<BillDto> getByUserId(Long userId, int page, int perPage) throws ServiceException;

    /**
     * Creates an bill by list of rooms, defined by bookRoomsForm, and by order, contaions check-in date,
     * check-out date etc.
     * @param bookRoomsForm contains list of rooms for making bill
     * @param order order for booking
     * @throws ServiceException
     */
    void makeBill(BookRoomsForm bookRoomsForm, OrderDto order) throws ServiceException;

    /**
     * Cancels booking, if it possible
     * @param billId id of canceled booking
     * @throws ServiceException
     */
    void cancelBooking(Long billId) throws ServiceException;

    /**
     * Pay bill
     * @param billId id of bill for pay
     * @throws ServiceException
     */
    void payBIll(Long billId) throws ServiceException;

    /**
     * Deletes bill, sets bill status as deleted
     * @param billId id of bill to delete
     * @throws ServiceException
     */
    void deleteBill(Long billId) throws ServiceException;

}
