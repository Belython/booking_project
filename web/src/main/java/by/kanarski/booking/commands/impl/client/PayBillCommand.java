package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayBillCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            UserDto user = (UserDto) session.getAttribute(Parameter.USER);
//            long billId = Long.valueOf(request.getParameter(Parameter.BILL_TO_PAY));
//            BillDto billToPay = BillService.getInstance().getById(billId);
//            billToPay.setBillStatus(FieldValue.STATUS_PAID);
//            BillService.getInstance().update(billToPay);
//            List<BillDto> billDtoList = BillService.getInstance().getByUserId(user.getUserId(), 0, 100);
//            String paymentRecived = OperationMessageManager.PAYMENT_RECIVED.getLocalised();
//            request.setAttribute(Parameter.OPERATION_MESSAGE, paymentRecived);
//            session.setAttribute(Parameter.BILL_LIST, billDtoList);
//            page = PagePath.ACCOUNT;
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            handleServiceException(request, e);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }
}
