package by.kanarski.booking.filters;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.managers.OperationMessageManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        CommandType commandType = RequestParser.parseCommandType(httpServletRequest);
//        switch (commandType) {
//            case MAKEBILL: {
//                checkAuthorization(httpServletRequest);
//                break;
//            }
//            case GOTOROOMTYPEREDACTOR: {
//                checkRole(httpServletRequest);
//                break;
//            }
//            case GOTOROOMSREDACTOR: {
//                checkRole(httpServletRequest);
//                break;
//            }
//        }
//        if (commandType.name().equals(commandType.MAKEBILL.toString())) {
//            checkAuthorization(httpServletRequest);
//        }
//        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private UserDto checkAuthorization(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(Parameter.USER);
        if (userDto == null) {
            request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.AUTHORIZATION_ERRON.getLocalised());
            request.setAttribute(Parameter.COMMAND, "cancelAction");
        }
        return userDto;
    }

    private void checkRole(HttpServletRequest request) {
        UserDto userDto = checkAuthorization(request);
        String role = userDto.getRole();
        switch (role) {
            case FieldValue.ROLE_CLIENT: {
                request.setAttribute(Parameter.OPERATION_MESSAGE, OperationMessageManager.LOW_ACCESS_LEVEL.getLocalised());
                request.setAttribute(Parameter.COMMAND, "cancelAction");
            }
        }
    }

}
