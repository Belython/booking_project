package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
//        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
//        Locale locale = UserPreferences.getLocale();
//        ResourceBundle bundle = ResourceManager.OPERATION_MESSAGES.setLocale(locale).create();
        try {
            UserDto userDto = RequestParser.parseUserDto(request);
            if (areFieldsFullStocked(userDto)) {
                if (UserServiceImpl.getInstance().checkIsNewUser(userDto)) {
                    UserServiceImpl.getInstance().registrateUser(userDto);
                    page = PagePath.REGISTRATION_PAGE_PATH;
                    request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.SUCCESS_OPERATION.getLocalised());
                } else {
                    page = PagePath.REGISTRATION_PAGE_PATH;
                    request.setAttribute(Parameter.ERROR_USER_EXISTS, OperationMessageManager.USER_EXISTS.getLocalised());
                }
            } else {
                request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.EMPTY_FIELDS.getLocalised());
                page = PagePath.REGISTRATION_PAGE_PATH;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        } catch (NumberFormatException e) {
//            String exceptionMessage = bundle.getString(OperationMessageKey.INVALID_NUMBER_FORMAT);
            request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.INVALID_NUMBER_FORMAT.getLocalised());
            page = PagePath.REGISTRATION_PAGE_PATH;
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
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
