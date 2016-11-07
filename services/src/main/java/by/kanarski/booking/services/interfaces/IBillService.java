package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

public interface IBillService extends IService<BillDto> {

    List<BillDto> getByUserId(long userId) throws ServiceException;

}
