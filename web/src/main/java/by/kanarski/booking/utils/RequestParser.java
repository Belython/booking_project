package by.kanarski.booking.utils;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.*;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.impl.RoomServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private RequestParser() {
    }

    public static UserDto parseUserDto(ServletRequest request) {
        long userId = FieldValue.UNDEFINED_ID;
        if (request.getParameter(Parameter.USER_ID) != null) {
            userId = Long.valueOf(request.getParameter(Parameter.USER_ID));
        }
        String firstName = request.getParameter(Parameter.USER_FIRST_NAME);
        String lastName = request.getParameter(Parameter.USER_LAST_NAME);
        String email = request.getParameter(Parameter.USER_EMAIL);
        String login = request.getParameter(Parameter.USER_LOGIN);
        String password = request.getParameter(Parameter.USER_PASSWORD);
        String role = request.getParameter(Parameter.USER_ROLE);
        String userStatus = request.getParameter(Parameter.USER_STATUS);
        return new UserDto(userId, firstName, lastName, email, login, password, role, userStatus);
    }

    public static OrderDto parseOrderDto(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(Parameter.USER);
        int totalPersons = Integer.valueOf(request.getParameter(Parameter.ORDER_TOTAL_PERSONS));
        HotelDto hotelDto = parseHotelDto(request);
        String checkInDate = request.getParameter(Parameter.ORDER_CHECK_IN_DATE);
        String defaultFormattedCheckInDate = DateUtil.getDefaultLocaleDate(checkInDate);
        String checkOutDate = request.getParameter(Parameter.ORDER_CHECK_OUT_DATE);
        String defaultFormattedCheckOutDate = DateUtil.getDefaultLocaleDate(checkOutDate);
        return new OrderDto(userDto, hotelDto, totalPersons,
                defaultFormattedCheckInDate, defaultFormattedCheckOutDate);
    }

    public static HotelDto parseHotelDto(ServletRequest request) {
        long hotelId = FieldValue.UNDEFINED_ID;
        if (request.getParameter(Parameter.HOTEL_ID) != null) {
            hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
        }
        String country = request.getParameter(Parameter.LOCATION_COUNTRY);
        String city = request.getParameter(Parameter.LOCATION_CITY);
        LocationDto locationDto = new LocationDto(country, city);
        String hotelName = request.getParameter(Parameter.HOTEL_NAME);
        return new HotelDto(hotelId, locationDto, hotelName);
    }

    public static String parsePagePath(ServletRequest request) {
        return request.getParameter(Parameter.CURRENT_PAGE_PATH);
    }

    public static CommandType parseCommandType(HttpServletRequest request) {
        String commandName = (String) request.getAttribute(Parameter.COMMAND);
        if (commandName != null) {
            request.removeAttribute(Parameter.COMMAND);
        } else {
            commandName = request.getParameter(Parameter.COMMAND);
        }
        CommandType commandType = CommandType.LOGIN;
        if (commandName != null) {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        }
        return commandType;
    }

    public static Locale parseLocale(HttpServletRequest request) {
        String localeName = request.getParameter(Parameter.LOCALE);
        Locale locale = null;
        if (localeName == null) {
            HttpSession session = request.getSession();
            locale = (Locale) session.getAttribute(Parameter.LOCALE);
        } else {
            String fullLocaleRegexp = "[a-z]+_[A-Z]+";
            String language = null;
            String country = null;
            if (localeName.matches(fullLocaleRegexp)) {
                String languageOrCountryRegexp = "[a-zA-Z]+";
                Pattern pattern = Pattern.compile(languageOrCountryRegexp);
                Matcher matcher = pattern.matcher(localeName);
                matcher.find();
                language = matcher.group();
                matcher.find();
                country = matcher.group();
                locale = new Locale(language, country);
            } else {
                locale = new Locale(localeName);
            }
        }
        return locale;
    }

    public static Currency parseCurrency(HttpServletRequest request) {
        String currencyCode = request.getParameter(Parameter.CURRENCY);
        Currency currency = null;
        if (currencyCode == null) {
            HttpSession session = request.getSession();
            currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        } else {
            currency = Currency.getInstance(currencyCode);
        }
        return currency;
    }

    public static BillDto parseBillDto(HttpServletRequest request) throws LocalisationException {
        HttpSession session = request.getSession();
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        UserDto userDto = orderDto.getUser();
        String checkInDate = orderDto.getCheckInDate();
        String checkOutDate = orderDto.getCheckOutDate();
        GlobalHotelDto selectedGlobalHotelDto = (GlobalHotelDto) session.getAttribute(Parameter.SELECTED_GLOBAL_HOTEL);
        HotelDto selectedHotelDto = DtoToEntityConverter.toHotelDto(selectedGlobalHotelDto);
        int totalPersons = orderDto.getTotalPersons();
        Set<RoomTypeDto> roomTypeDtoSet = selectedGlobalHotelDto.getRoomTypesCount().keySet();
        HashMap<RoomTypeDto, Integer> selectedRoomTypes = new HashMap<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoSet) {
            int roomTypeCount = Integer.valueOf(request.getParameter(roomTypeDto.getRoomTypeName()));
            if (roomTypeCount != 0) {
                selectedRoomTypes.put(roomTypeDto, roomTypeCount);
            }
        }
        List<RoomDto> selectedRoomList = AdminLogic.chooseRoomList(selectedRoomTypes, selectedGlobalHotelDto.getRoomList());
        int bookedDays = BillUtil.getBookedDays(checkInDate, checkOutDate);
        double paymentAmount = BillUtil.getPaymentAmount(bookedDays, selectedRoomList);
        return new BillDto(userDto, totalPersons, checkInDate, checkOutDate, selectedHotelDto,
                selectedRoomList, paymentAmount);
    }



    public static List<RoomDto> parseRoomDtoList(HttpServletRequest request) throws ServiceException{
        List<RoomDto> roomDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] roomIdArray = null;
        String[] hotelIdArray = null;
        String[] roomTypeIdArray = null;
        String[] roomNumberArray = null;
        String[] roomStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.ROOM_ID: {
                    roomIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_ID: {
                    hotelIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_ID: {
                    roomTypeIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_NUMBER: {
                    roomNumberArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_STATUS: {
                    roomStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        HttpSession session = request.getSession();
        for (int i = 0; i < roomIdArray.length; i++) {
            List<HotelDto> hotelDtoList = (List<HotelDto>) session.getAttribute(Parameter.HOTEL_LIST);
            List<RoomTypeDto> roomTypeDtoList = (List<RoomTypeDto>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
            long roomId = Long.valueOf(roomIdArray[i]);
            long hotelId = Long.valueOf(hotelIdArray[i]);
            long roomTypelId = Long.valueOf(roomTypeIdArray[i]);
            int roomNumber = Integer.valueOf(roomNumberArray[i]);
            HotelDto requestedHotelDto = null;
            RoomTypeDto requestedRoomTypeDto = null;
            for (HotelDto hotelDto : hotelDtoList) {
                if (hotelDto.getHotelId() == hotelId) {
                    requestedHotelDto = hotelDto;
                }
            }
            for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
                if (roomTypeDto.getRoomTypeId() == roomTypelId) {
                    requestedRoomTypeDto = roomTypeDto;

                }
            }
            // TODO: 25.09.2016 Не хочу заморачиваться с датами номера, буду получать их из базы данных
            TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
            RoomDto roomDto = new RoomDto(roomId, requestedHotelDto, requestedRoomTypeDto, roomNumber, bookedDates, roomStatusArray[i]);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static List<RoomTypeDto> parseRoomTypeDtoList(HttpServletRequest request) throws ServiceException{
        List<RoomTypeDto> roomTypeDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] roomTypeIdArray = null;
        String[] roomTypeNameArray = null;
        String[] maxPersonsArray = null;
        String[] pricePerNightArray = null;
        String[] facilitiesArray = null;
        String[] roomTypeStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.ROOM_TYPE_ID: {
                    roomTypeIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_NAME: {
                    roomTypeNameArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_MAX_PERSONS: {
                    maxPersonsArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_PRICE_PER_NIGHT: {
                    pricePerNightArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_FACILITIES: {
                    facilitiesArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_STATUS: {
                    roomTypeStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        for (int i = 0; i < roomTypeIdArray.length; i++) {
            long roomTypeId = Long.valueOf(roomTypeIdArray[i]);
            String roomTypeName = roomTypeNameArray[i];
            int maxPersons = Integer.valueOf(maxPersonsArray[i]);
            double pricePerNight = Double.valueOf(pricePerNightArray[i]);
            String facilities = facilitiesArray[i];
            String roomTypeStatus = roomTypeStatusArray[i];
            RoomTypeDto roomTypeDto = new RoomTypeDto(roomTypeId, roomTypeName, maxPersons, pricePerNight,
                    facilities, roomTypeStatus);
            roomTypeDtoList.add(roomTypeDto);
        }
        return roomTypeDtoList;
    }

    public static List<LocationDto> parseLocationDtoList(HttpServletRequest request) throws ServiceException{
        List<LocationDto> locationDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] locationIdArray = null;
        String[] countryArray = null;
        String[] cityArray = null;
        String[] locationStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.LOCATION_ID: {
                    locationIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.LOCATION_COUNTRY: {
                    countryArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.LOCATION_CITY: {
                    cityArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.LOCATION_STATUS: {
                    locationStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        for (int i = 0; i < locationIdArray.length; i++) {
            long locationId = Long.valueOf(locationIdArray[i]);
            String contry = countryArray[i];
            String city = cityArray[i];
            String locationStatus = locationStatusArray[i];
            LocationDto locationDto = new LocationDto(locationId, contry, city, locationStatus);
            locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }


    public static boolean isAjaxRequest(HttpServletRequest request) {String stringValue = request.getParameter(Parameter.IS_AJAX_REQUEST);
        return Boolean.parseBoolean(stringValue);
    }

    public static LocationDto parseLocationDto(HttpServletRequest request) {
        long locationId = Long.valueOf(request.getParameter(Parameter.LOCATION_ID));
        String contry = request.getParameter(Parameter.LOCATION_COUNTRY);
        String city = request.getParameter(Parameter.LOCATION_CITY);
        String locationStatus = request.getParameter(Parameter.LOCATION_STATUS);
        return new LocationDto(locationId, contry, city, locationStatus);
    }

    public static RoomTypeDto parseRoomTypeDto(HttpServletRequest request) {
        long roomTypeId = Long.valueOf(request.getParameter(Parameter.ROOM_TYPE_ID));
        String roomTypeName = request.getParameter(Parameter.ROOM_TYPE_NAME);
        int maxPersons = Integer.valueOf(request.getParameter(Parameter.ROOM_TYPE_MAX_PERSONS));
        double pricePerNight = Double.valueOf(request.getParameter(Parameter.ROOM_TYPE_PRICE_PER_NIGHT));
        String facilities = request.getParameter(Parameter.ROOM_TYPE_FACILITIES);
        String roomTypeStatus = request.getParameter(Parameter.ROOM_TYPE_STATUS);
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons, pricePerNight,
                facilities, roomTypeStatus);
    }

    public static RoomDto parseRoomDto(HttpServletRequest request) {
        String strRoomId = request.getParameter(Parameter.ROOM_ID);
        if ((strRoomId == null) || (strRoomId.isEmpty())) {
            strRoomId = "-1";
        }
        long roomId = Long.valueOf(strRoomId);
        HotelDto hotelDto = parseHotelDto(request);
        RoomTypeDto roomTypeDto = parseRoomTypeDto(request);
        String strRoomNumber = request.getParameter(Parameter.ROOM_NUMBER);
        if ((strRoomNumber == null) || (strRoomNumber.isEmpty())) {
            strRoomNumber = "-1";
        }
        int roomNumber = Integer.valueOf(strRoomNumber);
        TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
        String roomStatus = request.getParameter(Parameter.ROOM_STATUS);
        RoomDto roomDto = new RoomDto(roomId, hotelDto, roomTypeDto, roomNumber, bookedDates, roomStatus);
        return roomDto;
    }

    public static String parseFormName(HttpServletRequest request) {
        return request.getParameter(Parameter.FORM_NAME);
    }

    //В UI не работаем с bookedDates, берем из базы данных
    private static TreeMap<String, String> likeParseBookedDates(long roomId, HttpServletRequest request){
        HttpSession session = request.getSession();
        TreeMap<String, String> bookedDates;
        if (roomId != FieldValue.UNDEFINED_ID) {
            RoomDto roomDto = null;
            try {
                roomDto = RoomServiceImpl.getInstance().getById(roomId);
            }
            catch (ServiceException e) {
                e.printStackTrace();
            }
            bookedDates = roomDto.getBookedDates();
        } else {
            bookedDates = new TreeMap<>();
        }
        return bookedDates;
    }
}
