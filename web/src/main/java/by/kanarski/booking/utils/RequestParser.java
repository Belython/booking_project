package by.kanarski.booking.utils;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.RegExp;
import by.kanarski.booking.dto.*;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;

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
        Long userId = null;
        String userIdAsString = request.getParameter(Parameter.USER_ID);
        if (!StringUtils.isBlank(userIdAsString)) {
            userId = Long.valueOf(userIdAsString);
        }
        String firstName = request.getParameter(Parameter.USER_FIRST_NAME);
        String lastName = request.getParameter(Parameter.USER_LAST_NAME);
        String email = request.getParameter(Parameter.USER_EMAIL);
        String login = request.getParameter(Parameter.USER_LOGIN);
        String password = request.getParameter(Parameter.USER_PASSWORD);
        String role = request.getParameter(Parameter.USER_ROLE);
        String userStatus = request.getParameter(Parameter.USER_STATUS);
        if (StringUtils.isBlank(role)) {
            role = FieldValue.ROLE_USER;
        }
        if (StringUtils.isBlank(userStatus)) {
            userStatus = FieldValue.STATUS_ACTIVE;
        }
        return new UserDto(userId, firstName, lastName, email, login, password, role, userStatus);
    }

//    public static OrderDto parseOrderDto(HttpServletRequest request) throws ServiceException {
//        HttpSession session = request.getSession();
//        UserDto userDto = (UserDto) session.getAttribute(Parameter.USER);
//        Integer totalPersons = Integer.valueOf(request.getParameter(Parameter.ORDER_TOTAL_PERSONS));
//        Integer totalRooms = Integer.valueOf(request.getParameter(Parameter.ORDER_TOTAL_ROOMS));
////        HotelDto hotelDto = parseHotelDto(request);
//        HotelDto hotelDto = parseDestinationDto(request).getHotelDto();
//        String checkInDate = request.getParameter(Parameter.ORDER_CHECK_IN_DATE);
//        String defaultFormattedCheckInDate = DateUtil.getDefaultLocaleDate(checkInDate);
//        String checkOutDate = request.getParameter(Parameter.ORDER_CHECK_OUT_DATE);
//        String defaultFormattedCheckOutDate = DateUtil.getDefaultLocaleDate(checkOutDate);
//        return new OrderDto(userDto, hotelDto, totalPersons, totalRooms,
//                defaultFormattedCheckInDate, defaultFormattedCheckOutDate);
//    }

    public static DestinationDto parseDestinationDto(HttpServletRequest request) {
        String destination = request.getParameter(Parameter.DESTINATION);
        DestinationDto destinationDto = new DestinationDto();
        if (!StringUtils.isBlank(destination)) {
            Pattern destinationPattern = Pattern.compile(RegExp.RAW_DESTINATION);
            Matcher destinationMatcher = destinationPattern.matcher(destination);
            while (destinationMatcher.find()) {
                String rawParameter = destinationMatcher.group();
                String parameter = rawParameter.replaceAll(RegExp.COMMAS, "").trim();
                destinationDto.addParameter(parameter);
            }
            String country = getFirstInList(destination);
            String city = getNInList(destination);
            String hotelName = getLastInList(destination);
            if (city == null) {
                city = hotelName;
                hotelName = FieldValue.ANY_HOTEL;
            }
            HotelDto hotelDto = new HotelDto(country, city, hotelName);
            destinationDto.setHotelDto(hotelDto);
        }
        return destinationDto;
    }

    public static HotelDto parseHotelDto(ServletRequest request) throws ServiceException {
        Long hotelId = null;
        if (request.getParameter(Parameter.HOTEL_ID) != null) {
            hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
        }
        Long locationId = Long.valueOf(request.getParameter(Parameter.LOCATION_ID));
//        String country = request.getParameter(Parameter.LOCATION_COUNTRY);
//        String city = request.getParameter(Parameter.LOCATION_CITY);
//        LocationDto locationDto = LocationService.getInstance().getById(locationId);
        String hotelName = request.getParameter(Parameter.HOTEL_NAME);
        String hotelStatus = request.getParameter(Parameter.HOTEL_STATUS);
//        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
        return null;
    }

    public static String parsePagePath(ServletRequest request) {
        return request.getParameter(Parameter.CURRENT_PAGE_PATH);
    }

//    public static CommandType parseCommandType(HttpServletRequest request) {
//        String commandName = (String) request.getAttribute(Parameter.COMMAND);
//        if (commandName != null) {
//            request.removeAttribute(Parameter.COMMAND);
//        } else {
//            commandName = request.getParameter(Parameter.COMMAND);
//        }
//        CommandType commandType = CommandType.GOTOMAIN;
//        if (commandName != null) {
//            commandType = CommandType.valueOf(commandName.toUpperCase());
//        }
//        return commandType;
//    }

//    public static Locale parseLocale(HttpServletRequest request) {
//        String localeName = request.getParameter(Parameter.CURRENT_LOCALE);
//        Locale locale = null;
//        if (localeName == null) {
//            HttpSession session = request.getSession();
//            locale = (Locale) session.getAttribute(Parameter.CURRENT_LOCALE);
//        } else {
//            String fullLocaleRegexp = "[a-z]+_[A-Z]+";
//            String language = null;
//            String country = null;
//            if (localeName.matches(fullLocaleRegexp)) {
//                String languageOrCountryRegexp = "[a-zA-Z]+";
//                Pattern pattern = Pattern.compile(languageOrCountryRegexp);
//                Matcher matcher = pattern.matcher(localeName);
//                matcher.find();
//                language = matcher.group();
//                matcher.find();
//                country = matcher.group();
//                locale = new Locale(language, country);
//            } else {
//                locale = new Locale(localeName);
//            }
//        }
//        return locale;
//    }

//    public static Currency parseCurrency(HttpServletRequest request) {
//        String currencyCode = request.getParameter(Parameter.CURRENT_CURRENCY);
//        Currency currency = null;
//        if (currencyCode == null) {
//            HttpSession session = request.getSession();
//            currency = (Currency) session.getAttribute(Parameter.CURRENT_CURRENCY);
//        } else {
//            currency = Currency.getInstance(currencyCode);
//        }
//        return currency;
//    }

    public static BillDto parseBillDto(HttpServletRequest request) throws LocalisationException {
        HttpSession session = request.getSession();
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        UserDto userDto = orderDto.getUser();
        String checkInDate = orderDto.getCheckInDate();
        String checkOutDate = orderDto.getCheckOutDate();
        UserHotelDto selectedUserHotelDto = (UserHotelDto) session.getAttribute(Parameter.SELECTED_USER_HOTEL);
        HotelDto selectedHotelDto = DtoToEntityConverter.toHotelDto(selectedUserHotelDto);
        int totalPersons = orderDto.getTotalPersons();
        Set<RoomTypeDto> roomTypeDtoSet = selectedUserHotelDto.getRoomTypesCount().keySet();
        HashMap<RoomTypeDto, Integer> selectedRoomTypes = new HashMap<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoSet) {
            int roomTypeCount = Integer.valueOf(request.getParameter(roomTypeDto.getRoomTypeName()));
            if (roomTypeCount != 0) {
                selectedRoomTypes.put(roomTypeDto, roomTypeCount);
            }
        }
//        List<Room> roomList = RoomService.getInstance()
        List<RoomDto> selectedRoomList = AdminLogic.chooseRoomList(selectedRoomTypes, selectedUserHotelDto.getRoomList());
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
            String roomStatus = roomStatusArray[i];
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
//            TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
            RoomDto roomDto = new RoomDto(roomId, requestedHotelDto, requestedRoomTypeDto, roomNumber, roomStatus);
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

    public static List<UserDto> parseUserDtoList(HttpServletRequest request) throws ServiceException{
        List<UserDto> userDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] userIdArray = null;
        String[] firstNameArray = null;
        String[] lastNameArray = null;
        String[] emailArray = null;
        String[] loginArray = null;
        String[] passwordArray = null;
        String[] roleArray = null;
        String[] userStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.USER_ID: {
                    userIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_FIRST_NAME: {
                    firstNameArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_LAST_NAME: {
                    lastNameArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_EMAIL: {
                    emailArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_LOGIN: {
                    loginArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_PASSWORD: {
                    passwordArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_ROLE: {
                    roleArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.USER_STATUS: {
                    userStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        for (int i = 0; i < userIdArray.length; i++) {
            long userId = Long.valueOf(userIdArray[i]);
            String firstName = firstNameArray[i];
            String lastName = lastNameArray[i];
            String email = emailArray[i];
            String login = loginArray[i];
            String password = passwordArray[i];
            String role = roleArray[i];
            String userStatus = userStatusArray[i];
            UserDto userDto = new UserDto(userId, firstName, lastName, email, login, password, role, userStatus);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public static List<HotelDto> parseHotelDtoList(HttpServletRequest request) throws ServiceException{
        List<HotelDto> hotelDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] hotelIdArray = null;
        String[] locationIdArray = null;
        String[] hotelNameArray = null;
        String[] hotelStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.HOTEL_ID: {
                    hotelIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.LOCATION_ID: {
                    locationIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_NAME: {
                    hotelNameArray = parameterMap.get(hotelNameArray);
                }
                case Parameter.HOTEL_STATUS: {
                    hotelStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        HttpSession session = request.getSession();
        for (int i = 0; i < hotelIdArray.length; i++) {
            List<LocationDto> locationDtoList = (List<LocationDto>) session.getAttribute(Parameter.LOCATION_LIST);
            long hotelId = Long.valueOf(hotelIdArray[i]);
            long locationId = Long.valueOf(locationIdArray[i]);
            String hotelName = hotelNameArray[i];
            String hotelStatus = hotelStatusArray[i];
            LocationDto requestedLocationDto = null;
            for (LocationDto locationDto : locationDtoList) {
                if (locationDto.getLocationId() == locationId) {
                    requestedLocationDto = locationDto;
                }
            }
            HotelDto hotelDto = new HotelDto(hotelId, requestedLocationDto, hotelName, hotelStatus);
            hotelDtoList.add(hotelDto);
        }
        return hotelDtoList;
    }

//    public static List<BillDto> parseBillDtoList(HttpServletRequest request) throws ServiceException{
//        List<BillDto> billDtoList = new ArrayList<>();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Set<String> parameterSet = parameterMap.keySet();
//        String[] billIdArray = null;
//        String[] hotelIdArray = null;
//        String[] billTypeIdArray = null;
//        String[] billNumberArray = null;
//        String[] billStatusArray = null;
//        for (String parameter : parameterSet) {
//            switch (parameter) {
//                case Parameter.BILL_ID: {
//                    billIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.HOTEL_ID: {
//                    hotelIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.BILL_TYPE_ID: {
//                    billTypeIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.BILL_NUMBER: {
//                    billNumberArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.BILL_STATUS: {
//                    billStatusArray = parameterMap.get(parameter);
//                    break;
//                }
//            }
//        }
//        HttpSession session = request.getSession();
//        for (int i = 0; i < billIdArray.length; i++) {
//            List<HotelDto> hotelDtoList = (List<HotelDto>) session.getAttribute(Parameter.HOTEL_LIST);
//            List<BillTypeDto> billTypeDtoList = (List<BillTypeDto>) session.getAttribute(Parameter.BILL_TYPE_LIST);
//            long billId = Long.valueOf(billIdArray[i]);
//            long hotelId = Long.valueOf(hotelIdArray[i]);
//            long billTypelId = Long.valueOf(billTypeIdArray[i]);
//            int billNumber = Integer.valueOf(billNumberArray[i]);
//            String billStatus = billStatusArray[i];
//            HotelDto requestedHotelDto = null;
//            BillTypeDto requestedBillTypeDto = null;
//            for (HotelDto hotelDto : hotelDtoList) {
//                if (hotelDto.getHotelId() == hotelId) {
//                    requestedHotelDto = hotelDto;
//                }
//            }
//            for (BillTypeDto billTypeDto : billTypeDtoList) {
//                if (billTypeDto.getBillTypeId() == billTypelId) {
//                    requestedBillTypeDto = billTypeDto;
//
//                }
//            }
//            // TODO: 25.09.2016 Не хочу заморачиваться с датами номера, буду получать их из базы данных
//            TreeMap<String, String> bookedDates = likeParseBookedDates(billId, request);
//            BillDto billDto = new BillDto(billId, requestedHotelDto, requestedBillTypeDto, billNumber, bookedDates, billStatus);
//            billDtoList.add(billDto);
//        }
//        return billDtoList;
//    }

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

    public static RoomDto parseRoomDto(HttpServletRequest request) throws ServiceException {
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
//        TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
        String roomStatus = request.getParameter(Parameter.ROOM_STATUS);
        return  new RoomDto(roomId, hotelDto, roomTypeDto, roomNumber, roomStatus);
    }

    public static String parseFormName(HttpServletRequest request) {
        return request.getParameter(Parameter.FORM_NAME);
    }

    //В UI не работаем с bookedDates, берем из базы данных
//    private static TreeMap<String, String> likeParseBookedDates(long roomId, HttpServletRequest request){
//        HttpSession session = request.getSession();
//        TreeMap<String, String> bookedDates;
//        if (roomId != FieldValue.UNDEFINED_ID) {
//            RoomDto roomDto = null;
//            try {
//                roomDto = RoomService.getInstance().getById(roomId);
//            }
//            catch (ServiceException e) {
//                e.printStackTrace();
//            }
////            bookedDates = roomDto.getBookedDates();
//        } else {
//            bookedDates = new TreeMap<>();
//        }
//        return bookedDates;
//    }

    private static String getFirstInList(String listAsString) {
        Pattern pattern = Pattern.compile(RegExp.FIRST_IN_LIST);
        Matcher matcher = pattern.matcher(listAsString);
        String result = null;
        if (matcher.find()) {
            String rawResult = matcher.group();
            result = rawResult.substring(0, rawResult.length() - 2);
        }
        return result;
    }

    private static String getLastInList(String listAsString) {
        Pattern pattern = Pattern.compile(RegExp.LAST_IN_LIST);
        Matcher matcher = pattern.matcher(listAsString);
        String result = null;
        if (matcher.find()) {
            String rawResult = matcher.group();
            result = rawResult.substring(2);
        }
        return result;
    }

    private static String getNInList(String listAsString) {
        Pattern pattern = Pattern.compile(RegExp.N_IN_LIST);
        Matcher matcher = pattern.matcher(listAsString);
        String result = null;
        if (matcher.find()) {
            String rawResult = matcher.group();
            result = rawResult.substring(2, rawResult.length() - 2);
        }
        return result;
    }
}
