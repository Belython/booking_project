package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.RegExp;
import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
public class DestinationDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String destination;
    private List<String> parameterList = new ArrayList<>();
    private HotelDto hotelDto;

    public void setDestination(String destination) {
        this.destination = destination;
        parseParameterList(destination);

    }

    public void addParameter(String parameter) {
        parameterList.add(parameter);
    }

    private void parseParameterList(String destination) {
//        DestinationDto destinationDto = new DestinationDto();
        List<String> parameterList = new ArrayList<>();
        if (!StringUtils.isBlank(destination)) {
            Pattern destinationPattern = Pattern.compile(RegExp.RAW_DESTINATION);
            Matcher destinationMatcher = destinationPattern.matcher(destination);
            while (destinationMatcher.find()) {
                String rawParameter = destinationMatcher.group();
                String parameter = rawParameter.replaceAll(RegExp.COMMAS, "").trim();
                parameterList.add(parameter);
            }
            setParameterList(parameterList);
            int parameterListSize = parameterList.size();
            String country = (parameterListSize >= 1) ? parameterList.get(0) : null;
            String city = (parameterListSize >= 2) ? parameterList.get(1) : null;
            String hotelName = (parameterListSize >= 3) ? parameterList.get(2) : FieldValue.ANY_HOTEL;
            HotelDto hotelDto = new HotelDto(country, city, hotelName);
            setHotelDto(hotelDto);
        }
    }

    public static HotelDto getHotelDto(String destination) {
        HotelDto hotelDto = null;
        if (!StringUtils.isBlank(destination)) {
            List<String> parameterList = new ArrayList<>();
            Pattern destinationPattern = Pattern.compile(RegExp.RAW_DESTINATION);
            Matcher destinationMatcher = destinationPattern.matcher(destination);
            while (destinationMatcher.find()) {
                String rawParameter = destinationMatcher.group();
                String parameter = rawParameter.replaceAll(RegExp.COMMAS, "").trim();
                parameterList.add(parameter);
            }
            int parameterListSize = parameterList.size();
            String country = (parameterListSize >= 1) ? parameterList.get(0) : null;
            String city = (parameterListSize >= 2) ? parameterList.get(1) : null;
            String hotelName = (parameterListSize >= 3) ? parameterList.get(2) : FieldValue.ANY_HOTEL;
            hotelDto = new HotelDto(country, city, hotelName);
        }
        return hotelDto;
    }

}
