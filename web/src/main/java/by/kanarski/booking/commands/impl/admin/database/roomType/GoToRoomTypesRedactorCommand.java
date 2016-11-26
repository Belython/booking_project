package by.kanarski.booking.commands.impl.admin.database.roomType;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.RoomTypeService;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class GoToRoomTypesRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page;
        HttpSession session = request.getSession();
        try {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ROOM_TYPES_REDACTOR_PATH;
            List<RoomTypeDto> roomTypeDtoList = RoomTypeService.getInstance().getAll(0, 100);
            List<FieldDescriptor<RoomTypeDto>> descriptorList = new ArrayList<>();
            for (RoomTypeDto roomTypeDto: roomTypeDtoList) {
                LinkedHashMap<String, FieldDescriptor> roomTypeDescriptors = new LinkedHashMap<>();
                FieldDescriptor roomTypeIdDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor roomTypeNameDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor maxPersonsDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor pricePerNightDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor facilitiesDescriptor = FieldBuilder.buildFreePrimitive();
                FieldDescriptor roomTypeStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_ID, roomTypeIdDescriptor);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_NAME, roomTypeNameDescriptor);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsDescriptor);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightDescriptor);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesDescriptor);
                roomTypeDescriptors.put(Parameter.ROOM_TYPE_STATUS, roomTypeStatusDescriptor);
                FieldDescriptor<RoomTypeDto> roomTypeDtoDescriptor = FieldBuilder.buildEntity(roomTypeDescriptors, roomTypeDto);
                descriptorList.add(roomTypeDtoDescriptor);
            }
            session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_ROOM_TYPES);
            session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
            session.setAttribute(Parameter.ROOM_TYPE_LIST, roomTypeDtoList);
            session.setAttribute(Parameter.STATUS_LIST, FieldValue.STATUS_LIST);
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            servletAction = ServletAction.FORWARD_PAGE;
            String errorMessage = OperationMessageManager.ERROR_DATABASE.getLocalised();
            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
