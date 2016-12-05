package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class CommandHelper {

    private CommandHelper() {

    }

    public static void writeAjaxResponse(HttpServletResponse response, String responseText) {
        try {
            String contentType = "text/html; charset=UTF-8";
            response.setContentType(contentType);
            PrintWriter out = response.getWriter();
            out.write(responseText);
            out.flush();
            out.close();
        } catch (IOException e) {
            SystemLogger.getInstance().logError(CommandHelper.class, e.getMessage(), e);
        }
    }

    public static ServletAction generateServletAction(HttpServletRequest request,
                                                      HttpServletResponse response, String responseText) {
        ServletAction servletAction;
        String page;
        HttpSession session = request.getSession();
        if (RequestParser.isAjaxRequest(request)) {
            servletAction = ServletAction.AJAX_INCLUDE_REQUEST;
            CommandHelper.writeAjaxResponse(response, responseText);
            page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        } else {
            servletAction = ServletAction.FORWARD_PAGE;
            request.setAttribute(Parameter.OPERATION_MESSAGE, responseText);
            page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        }
        servletAction.setPage(page);
        return servletAction;
    }

}
