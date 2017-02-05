package by.kanarski.booking.utils;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.RegExp;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private RequestParser() {
    }

    public static DestinationDto parseDestinationDto(HttpServletRequest request) {
        String destination = request.getParameter(Parameter.DESTINATION);
        DestinationDto destinationDto = new DestinationDto();
        if (!StringUtils.isBlank(destination)) {
            List<String> parameterList = new ArrayList<>();
            Pattern destinationPattern = Pattern.compile(RegExp.RAW_DESTINATION);
            Matcher destinationMatcher = destinationPattern.matcher(destination);
            while (destinationMatcher.find()) {
                String rawParameter = destinationMatcher.group();
                String parameter = rawParameter.replaceAll(RegExp.COMMAS, "").trim();
                parameterList.add(parameter);
            }
            destinationDto.setParameterList(parameterList);
            int parameterListSize = parameterList.size();
            String country = (parameterListSize >= 1) ? parameterList.get(0) : null;
            String city = (parameterListSize >= 2) ? parameterList.get(1) : null;
            String hotelName = (parameterListSize >= 3) ? parameterList.get(2) : FieldValue.ANY_HOTEL;
//            if (city == null) {
//                city = hotelName;
//                hotelName = FieldValue.ANY_HOTEL;
//            }
            HotelDto hotelDto = new HotelDto(country, city, hotelName);
            destinationDto.setHotelDto(hotelDto);
        }
        return destinationDto;
    }



//    private static String getFirstInList(String listAsString) {
//        Pattern pattern = Pattern.compile(RegExp.FIRST_IN_LIST);
//        Matcher matcher = pattern.matcher(listAsString);
//        String result = null;
//        if (matcher.find()) {
//            String rawResult = matcher.group();
//            result = rawResult.substring(0, rawResult.length() - 2);
//        }
//        return result;
//    }
//
//    private static String getLastInList(String listAsString) {
//        Pattern pattern = Pattern.compile(RegExp.LAST_IN_LIST);
//        Matcher matcher = pattern.matcher(listAsString);
//        String result = null;
//        if (matcher.find()) {
//            String rawResult = matcher.group();
//            result = rawResult.substring(2);
//        }
//        return result;
//    }
//
//    private static String getNInList(String listAsString) {
//        Pattern pattern = Pattern.compile(RegExp.N_IN_LIST);
//        Matcher matcher = pattern.matcher(listAsString);
//        String result = null;
//        if (matcher.find()) {
//            String rawResult = matcher.group();
//            result = rawResult.substring(2, rawResult.length() - 2);
//        }
//        return result;
//    }
}
