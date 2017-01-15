package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.StateValue;
import by.kanarski.booking.constants.Pages;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Controller
public class AdminController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "alterUser")
    public String alterUser(UserDto user, String subCommand, Model model, HttpSession session) {
        String currentViewName = (String) session.getAttribute(Parameter.CURRENT_VIEW_NAME);

        try {
            switch (subCommand) {
                case Value.ADD_NEW: {
                    userService.add(user);
                    break;
                }
                case Value.CHANGE_EXISTING: {
                    userService.update(user);
                    break;
                }
            }

            List<UserDto> newUserDtoList = userService.getAll();
//            session.setAttribute(Parameter.USER_LIST, newUserDtoList);
//            String responseText = OperationMessageManager.DATABASE_CHANGE_SUCCES.getLocalised();
//            servletAction = CommandHelper.generateServletAction(request, response, responseText);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }
        return currentViewName;
    }

    @RequestMapping(value = "users_redactor")
    public String goToUsersRedactor(Model model) {
        String page = Pages.PAGE_USERS_REDACTOR;
        try {
            List<UserDto> userDtoList = userService.getAll();
            List<FieldDescriptor<UserDto>> descriptorList = new ArrayList<>();
            for (UserDto userDto : userDtoList) {
                LinkedHashMap<String, FieldDescriptor> userDescriptors = new LinkedHashMap<>();
                FieldDescriptor userIdDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userFirstNameDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userLastNameDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userEmailDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userLoginDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userPasswordDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor userRoleDescriptor = FieldBuilder.buildPrimitive(StateValue.ROLE_LIST);
                FieldDescriptor userStatusDescriptor = FieldBuilder.buildPrimitive(StateValue.STATUS_LIST);
                userDescriptors.put(Parameter.USER_ID, userIdDescriptor);
                userDescriptors.put(Parameter.USER_FIRST_NAME, userFirstNameDescriptor);
                userDescriptors.put(Parameter.USER_LAST_NAME, userLastNameDescriptor);
                userDescriptors.put(Parameter.USER_EMAIL, userEmailDescriptor);
                userDescriptors.put(Parameter.USER_LOGIN, userLoginDescriptor);
                userDescriptors.put(Parameter.USER_PASSWORD, userPasswordDescriptor);
                userDescriptors.put(Parameter.USER_ROLE, userRoleDescriptor);
                userDescriptors.put(Parameter.USER_STATUS, userStatusDescriptor);
                FieldDescriptor<UserDto> userDtoDescriptor = FieldBuilder.buildEntity(userDescriptors, userDto);
                descriptorList.add(userDtoDescriptor);
            }
            model.addAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_USERS);
            model.addAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
            model.addAttribute(Parameter.USER_LIST, userDtoList);
            model.addAttribute(Parameter.STATUS_LIST, StateValue.STATUS_LIST);
            List<String> USER_COLUMN_LIST = Arrays.asList(
                    "tableRedactor.rowNumber",
                    "entityField.firstName",
                    "entityField.lastName",
                    "entityField.email",
                    "entityField.login",
                    "entityField.password",
                    "entityField.userRole",
                    "entityField.userStatus"
            );
            model.addAttribute("colNameList", USER_COLUMN_LIST);
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return "redactor";
    }

    @RequestMapping(value = "constrainRow")
    public String constrainRow(@SessionAttribute String currentViewName,
                               @RequestAttribute String formName,
                               UserDto user,
                               Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            switch (currentViewName) {
                case Pages.PAGE_USERS_REDACTOR: {
                    constrainUser(request, user);
                    break;
                }
            }
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }

//        String formName = RequestParser.parseFormName(request);
//        switch (formName) {
//            case Value.NEW_ENTITY_FORM: {
//                page = PagePath.NEW_TABLE_ROW;
//                break;
//            }
//            case Value.ALTER_ENTITY_FORM: {
//                page = PagePath.ALTER_TABLE_ROW;
//                break;
//            }
//        }
        return currentViewName;
    }

    private void constrainUser(HttpServletRequest request, UserDto userDto) throws ServiceException {

        LinkedHashMap<String, FieldDescriptor> userDescriptors = new LinkedHashMap<>();
        FieldDescriptor userIdDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userFirstNameDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userLastNameDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userEmailDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userLoginDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userPasswordDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userRoleDescriptor = FieldBuilder.buildPrimitive(StateValue.ROLE_LIST);
        FieldDescriptor userStatusDescriptor = FieldBuilder.buildPrimitive(StateValue.STATUS_LIST);
        userDescriptors.put(Parameter.USER_ID, userIdDescriptor);
        userDescriptors.put(Parameter.USER_FIRST_NAME, userFirstNameDescriptor);
        userDescriptors.put(Parameter.USER_LAST_NAME, userLastNameDescriptor);
        userDescriptors.put(Parameter.USER_EMAIL, userEmailDescriptor);
        userDescriptors.put(Parameter.USER_LOGIN, userLoginDescriptor);
        userDescriptors.put(Parameter.USER_PASSWORD, userPasswordDescriptor);
        userDescriptors.put(Parameter.USER_ROLE, userRoleDescriptor);
        userDescriptors.put(Parameter.USER_STATUS, userStatusDescriptor);
        FieldDescriptor<UserDto> userDtoDescriptor = FieldBuilder.buildEntity(userDescriptors, userDto);

        request.setAttribute(Parameter.DESCRIPTOR, userDtoDescriptor);
    }

    @RequestMapping(value = "admin")
    public String toAdminPage() {
        return Pages.PAGE_ADMIN;
    }

}
