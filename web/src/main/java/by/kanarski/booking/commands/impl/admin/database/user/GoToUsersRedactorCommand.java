package by.kanarski.booking.commands.impl.admin.database.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GoToUsersRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page;
//        HttpSession session = request.getSession();
//        try {
//            servletAction = ServletAction.FORWARD_PAGE;
//            page = PagePath.USERS_REDACTOR;
//            List<UserDto> userDtoList = UserService.getInstance().getAll(0, 100);
//            List<FieldDescriptor<UserDto>> descriptorList = new ArrayList<>();
//            for (UserDto userDto: userDtoList) {
//                LinkedHashMap<String, FieldDescriptor> userDescriptors = new LinkedHashMap<>();
//                FieldDescriptor userIdDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userFirstNameDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userLastNameDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userEmailDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userLoginDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userPasswordDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor userRoleDescriptor = FieldBuilder.buildPrimitive(FieldValue.ROLE_LIST);
//                FieldDescriptor userStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
//                userDescriptors.put(Parameter.USER_ID, userIdDescriptor);
//                userDescriptors.put(Parameter.USER_FIRST_NAME, userFirstNameDescriptor);
//                userDescriptors.put(Parameter.USER_LAST_NAME, userLastNameDescriptor);
//                userDescriptors.put(Parameter.USER_EMAIL, userEmailDescriptor);
//                userDescriptors.put(Parameter.USER_LOGIN, userLoginDescriptor);
//                userDescriptors.put(Parameter.USER_PASSWORD, userPasswordDescriptor);
//                userDescriptors.put(Parameter.USER_ROLE, userRoleDescriptor);
//                userDescriptors.put(Parameter.USER_STATUS, userStatusDescriptor);
//                FieldDescriptor<UserDto> userDtoDescriptor = FieldBuilder.buildEntity(userDescriptors, userDto);
//                descriptorList.add(userDtoDescriptor);
//            }
//            session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_USERS);
//            session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
//            session.setAttribute(Parameter.USER_LIST, userDtoList);
//            session.setAttribute(Parameter.STATUS_LIST, FieldValue.STATUS_LIST);
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            servletAction = ServletAction.FORWARD_PAGE;
//            String errorMessage = OperationMessageManager.ERROR_DATABASE.getLocalised();
//            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }
}
