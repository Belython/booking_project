package by.kanarski.booking.commands.impl.admin.database;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.*;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConstrainUtil;
import by.kanarski.booking.utils.RequestParser;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;

public class ConstrainRowCommand extends AbstractCommand{

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();

        String currentPagePath = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        try {

            switch (currentPagePath) {
                case PagePath.ROOMS_REDACTOR_PATH: {
                    constrainRoom(request);
                    break;
                }
                case PagePath.ROOM_TYPES_REDACTOR_PATH: {
                    constrainRoomType(request);
                    break;
                }
                case PagePath.LOCATIONS_REDACTOR_PATH: {
                    constrainLocation(request);
                    break;
                }
                case PagePath.USERS_REDACTOR_PATH: {
                    constrainUser(request);
                    break;
                }
                case PagePath.HOTELS_REDACTOR_PATH: {
                    constrainHotel(request);
                }
            }
        } catch (ServiceException e) {
            BookingSystemLogger.getInstance().logError(getClass(), e.getMessage(), e);
        }

        String formName = RequestParser.parseFormName(request);
        switch (formName) {
            case Value.NEW_ENTITY_FORM: {
                page = PagePath.NEW_TABLE_ROW_PATH;
                break;
            }
            case Value.ALTER_ENTITY_FORM: {
                page = PagePath.ALTER_TABLE_ROW_PATH;
                break;
            }
        }
        servletAction = ServletAction.AJAX_INCLUDE_REQUEST;
        servletAction.setPage(page);
        return servletAction;
    }

    private void constrainRoom(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        RoomDto roomDto = RequestParser.parseRoomDto(request);
        List<RoomTypeDto> roomTypeDtoList = (List<RoomTypeDto>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
        List<HotelDto> hotelDtoList = (List<HotelDto>) session.getAttribute(Parameter.HOTEL_LIST);

        LinkedHashMap<String, FieldDescriptor> roomFields = new LinkedHashMap<>();
        FieldDescriptor roomIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<HotelDto> hotelDtoFieldDescriptor = ConstrainUtil.byHotel(roomDto.getHotel(), hotelDtoList);
        FieldDescriptor<RoomTypeDto> roomTypeDtoFieldDescriptor = ConstrainUtil.byRoomType(roomDto.getRoomType(), roomTypeDtoList);
        roomDto.setHotel(hotelDtoFieldDescriptor.getOwner());
        roomDto.setRoomType(roomTypeDtoFieldDescriptor.getOwner());
        FieldDescriptor roomNumberFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor roomStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        roomFields.put(Parameter.ROOM_ID, roomIdFieldDescriptor);
        roomFields.put(Parameter.ROOM_HOTEL, hotelDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_TYPE, roomTypeDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_NUMBER, roomNumberFieldDescriptor);
        roomFields.put(Parameter.ROOM_STATUS, roomStatusFieldDescriptor);
        FieldDescriptor<RoomDto> roomDtoDescriptor = FieldBuilder.buildEntity(roomFields, roomDto);

        request.setAttribute(Parameter.DESCRIPTOR, roomDtoDescriptor);
    }

    private void constrainRoomType(HttpServletRequest request) {
        RoomTypeDto roomTypeDto = RequestParser.parseRoomTypeDto(request);

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

        request.setAttribute(Parameter.DESCRIPTOR, roomTypeDtoDescriptor);
    }

    private void constrainLocation(HttpServletRequest request) {
        LocationDto locationDto = RequestParser.parseLocationDto(request);

        LinkedHashMap<String, FieldDescriptor> locationDescriptors = new LinkedHashMap<>();
        FieldDescriptor locationIdDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor locationCountryDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor locationCityDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor locationStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        locationDescriptors.put(Parameter.LOCATION_ID, locationIdDescriptor);
        locationDescriptors.put(Parameter.LOCATION_COUNTRY, locationCountryDescriptor);
        locationDescriptors.put(Parameter.LOCATION_CITY, locationCityDescriptor);
        locationDescriptors.put(Parameter.LOCATION_STATUS, locationStatusDescriptor);
        FieldDescriptor<LocationDto> locationDtoDescriptor = FieldBuilder.buildEntity(locationDescriptors, locationDto);

        request.setAttribute(Parameter.DESCRIPTOR, locationDtoDescriptor);
    }

    private void constrainUser(HttpServletRequest request) {
        UserDto userDto = RequestParser.parseUserDto(request);

        LinkedHashMap<String, FieldDescriptor> userDescriptors = new LinkedHashMap<>();
        FieldDescriptor userIdDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userFirstNameDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userLastNameDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userEmailDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userLoginDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userPasswordDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor userRoleDescriptor = FieldBuilder.buildPrimitive(FieldValue.ROLE_LIST);
        FieldDescriptor userStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
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

    private void constrainHotel(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        HotelDto hotelDto = RequestParser.parseHotelDto(request);
        List<LocationDto> locationDtoList = (List<LocationDto>) session.getAttribute(Parameter.LOCATION_LIST);

        LinkedHashMap<String, FieldDescriptor> hotelFields = new LinkedHashMap<>();
        FieldDescriptor hotelIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<LocationDto> locationDtoFieldDescriptor = ConstrainUtil.byLocation(
                hotelDto.getLocation(), locationDtoList);
        hotelDto.setLocation(locationDtoFieldDescriptor.getOwner());
        FieldDescriptor hotelNameFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor hotelStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        hotelFields.put(Parameter.HOTEL_ID, hotelIdFieldDescriptor);
        hotelFields.put(Parameter.HOTEL_LOCATION, locationDtoFieldDescriptor);
        hotelFields.put(Parameter.HOTEL_NAME, hotelNameFieldDescriptor);
        hotelFields.put(Parameter.HOTEL_STATUS, hotelStatusFieldDescriptor);
        FieldDescriptor<HotelDto> hotelDtoEntity = FieldBuilder.buildEntity(hotelFields, hotelDto);

        request.setAttribute(Parameter.DESCRIPTOR, hotelDtoEntity);
    }
}
