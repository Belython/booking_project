package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IBillService extends IExtendedBaseService<Bill, BillDto> {

    List<BillDto> getByUserId(Long userId, int page, int perPage) throws ServiceException;

}
