package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            UserDto userDto = RequestParser.parseUserDto(request);
//            if (areFieldsFullStocked(userDto)) {
//                if (UserService.getInstance().isNewUser(userDto)) {
//                    UserService.getInstance().registrateUser(userDto);
//                    page = PagePath.INDEX;
//                    request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.SUCCESS_OPERATION.getLocalised());
//                } else {
//                    page = PagePath.INDEX;
//                    request.setAttribute(Parameter.ERROR_USER_EXISTS, OperationMessageManager.USER_EXISTS.getLocalised());
//                }
//            } else {
//                request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.EMPTY_FIELDS.getLocalised());
//                page = PagePath.INDEX;
//            }
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            handleServiceException(request, e);
//        } catch (NumberFormatException e) {
//            request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.INVALID_NUMBER_FORMAT.getLocalised());
//            page = PagePath.INDEX;
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }

    // TODO javascript???
    private boolean areFieldsFullStocked(UserDto userDto) {

        boolean isFullStocked = false;
        if (!userDto.getFirstName().isEmpty()
                & !userDto.getLastName().isEmpty()
                & !userDto.getLogin().isEmpty()
                & !userDto.getPassword().isEmpty()
                & !userDto.getEmail().isEmpty()) {
            isFullStocked = true;
        }
        return isFullStocked;
    }
}
