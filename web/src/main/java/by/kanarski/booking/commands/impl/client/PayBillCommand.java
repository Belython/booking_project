package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PayBillCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            UserDto user = (UserDto) session.getAttribute(Parameter.USER);
            long billId = Long.valueOf(request.getParameter(Parameter.BILL_TO_PAY));
            BillDto billToPay = BillServiceImpl.getInstance().getById(billId);
            billToPay.setBillStatus(FieldValue.STATUS_PAID);
            BillServiceImpl.getInstance().update(billToPay);
            List<BillDto> billDtoList = BillServiceImpl.getInstance().getByUserId(user.getUserId());
            String paymentRecived = OperationMessageManager.PAYMENT_RECIVED.getLocalised();
            request.setAttribute(Parameter.OPERATION_MESSAGE, paymentRecived);
            session.setAttribute(Parameter.BILL_LIST, billDtoList);
            page = PagePath.ACCOUNT_PAGE_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
