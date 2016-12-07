package by.kanarski.booking.commands.impl.admin.database.location;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.OperationMessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.LocationService;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class GoToLocationsRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page;
        HttpSession session = request.getSession();
        try {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.LOCATIONS_REDACTOR;
            List<LocationDto> locationDtoList = LocationService.getInstance().getAll(0, 100);
            List<FieldDescriptor<LocationDto>> descriptorList = new ArrayList<>();
            for (LocationDto locationDto: locationDtoList) {
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
                descriptorList.add(locationDtoDescriptor);
            }
            session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_LOCATIONS);
            session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
            session.setAttribute(Parameter.LOCATION_LIST, locationDtoList);
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
