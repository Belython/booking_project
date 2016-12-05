package by.kanarski.booking.commands.impl.admin.database.hotel;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GoToHotelsRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        try {
//            servletAction = ServletAction.FORWARD_PAGE;
//            page = PagePath.HOTELS_REDACTOR;
//            List<HotelDto> hotelDtoList = HotelService.getInstance().getAll(0, 100);
//            List<LocationDto> locationDtoList = LocationService.getInstance().getAll(0, 100);
//            List<FieldDescriptor<HotelDto>> descriptorList = new ArrayList<>();
//            for (HotelDto hotelDto: hotelDtoList) {
//                LinkedHashMap<String, FieldDescriptor> hotelFields = new LinkedHashMap<>();
//                FieldDescriptor hotelIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor<LocationDto> locationDtoFieldDescriptor = ConstrainUtil.byLocation(
//                        hotelDto.getLocation(), locationDtoList);
//                hotelDto.setLocation(locationDtoFieldDescriptor.getOwner());
//                FieldDescriptor hotelNameFieldDescriptor = FieldBuilder.buildFreePrimitive();
//                FieldDescriptor hotelStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
//                hotelFields.put(Parameter.HOTEL_ID, hotelIdFieldDescriptor);
//                hotelFields.put(Parameter.HOTEL_LOCATION, locationDtoFieldDescriptor);
//                hotelFields.put(Parameter.HOTEL_NAME, hotelNameFieldDescriptor);
//                hotelFields.put(Parameter.HOTEL_STATUS, hotelStatusFieldDescriptor);
//                FieldDescriptor<HotelDto> hotelDtoEntity = FieldBuilder.buildEntity(hotelFields, hotelDto);
//                descriptorList.add(hotelDtoEntity);
//            }
//            session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_HOTELS);
//            session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
//            session.setAttribute(Parameter.HOTEL_LIST, hotelDtoList);
//            session.setAttribute(Parameter.LOCATION_LIST, locationDtoList);
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
