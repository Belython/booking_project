package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class RefuseBillCommand extends AbstractCommand{

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            BillDto refusedBillDto = refuseBill(request);
            List<BillDto> newBillDtoList = getNewBillDtoList(session, refusedBillDto);
            session.setAttribute(Parameter.BILL_LIST, newBillDtoList);
            request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.SUCCESS_OPERATION.getLocalised());
            page = PagePath.ACCOUNT;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    private BillDto refuseBill(HttpServletRequest request) throws ServiceException{
        long refusedBillId = Long.valueOf(request.getParameter(Parameter.BILL_TO_REFUSE));
        BillDto refusedBillDto = BillService.getInstance().getById(refusedBillId);
//        String checkInDate = refusedBillDto.getCheckInDate();
//        List<RoomDto> refusedRoomDtoList = refusedBillDto.getRoomList();
//        for (RoomDto roomDto : refusedRoomDtoList) {
//            TreeMap<String, String> bookedDates = roomDto.getBookedDates();
//            if (bookedDates != null) {
//                bookedDates.remove(checkInDate);
//                //Да, знаю что лишнее
//                roomDto.setBookedDates(bookedDates);
//            }
//        }
        refusedBillDto.setBillStatus(FieldValue.STATUS_REFUSED);
//        RoomServiceImpl.getInstance().updateList(refusedRoomDtoList);
        BillService.getInstance().update(refusedBillDto);
        return refusedBillDto;
    }

    private List<BillDto> getNewBillDtoList(HttpSession session, BillDto refusedBillDto) {
        Locale locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENT_CURRENCY);
        long refusedBillId = refusedBillDto.getBillId();
        List<BillDto> billDtoList = (List<BillDto>) session.getAttribute(Parameter.BILL_LIST);
        for (BillDto billDto : billDtoList) {
            long billId = billDto.getBillId();
            if (billId == refusedBillId) {
                billDtoList.remove(billDto);
                billDtoList.add(refusedBillDto);
                break;
            }
        }
        return billDtoList;
    }
}
