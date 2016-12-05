package by.kanarski.booking.commands.impl.admin.database.room;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GoToRoomsRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            servletAction = ServletAction.FORWARD_PAGE;
//            page = PagePath.ROOMS_REDACTOR;
//            List<RoomDto> roomDtoList = RoomService.getInstance().getAll(0, 100);
//            List<HotelDto> hotelDtoList = HotelService.getInstance().getAll(0, 100);
//            List<RoomTypeDto> roomTypeDtoList = RoomTypeService.getInstance().getAll(0, 100);
//            List<FieldDescriptor<RoomDto>> descriptorList = new ArrayList<>();
//            for (RoomDto roomDto: roomDtoList) {
//                LinkedHashMap<String, FieldDescriptor> roomFields = new LinkedHashMap<>();
//                FieldDescriptor roomIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor<HotelDto> hotelDtoFieldDescriptor = ConstrainUtil.byHotel(roomDto.getHotel(), hotelDtoList);
//                FieldDescriptor<RoomTypeDto> roomTypeDtoFieldDescriptor = ConstrainUtil.byRoomType(roomDto.getRoomType(), roomTypeDtoList);
//                roomDto.setHotel(hotelDtoFieldDescriptor.getOwner());
//                roomDto.setRoomType(roomTypeDtoFieldDescriptor.getOwner());
//                FieldDescriptor roomNumberFieldDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor roomStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
//                roomFields.put(Parameter.ROOM_ID, roomIdFieldDescriptor);
//                roomFields.put(Parameter.ROOM_HOTEL, hotelDtoFieldDescriptor);
//                roomFields.put(Parameter.ROOM_TYPE, roomTypeDtoFieldDescriptor);
//                roomFields.put(Parameter.ROOM_NUMBER, roomNumberFieldDescriptor);
//                roomFields.put(Parameter.ROOM_STATUS, roomStatusFieldDescriptor);
//                FieldDescriptor<RoomDto> roomDtoEntity = FieldBuilder.buildEntity(roomFields, roomDto);
//                descriptorList.add(roomDtoEntity);
//            }
//            session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_ROOMS);
//            session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
//            session.setAttribute(Parameter.ROOM_LIST, roomDtoList);
//            session.setAttribute(Parameter.HOTEL_LIST, hotelDtoList);
//            session.setAttribute(Parameter.ROOM_TYPE_LIST, roomTypeDtoList);
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
