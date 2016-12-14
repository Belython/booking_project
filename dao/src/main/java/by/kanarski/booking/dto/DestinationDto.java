package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
public class DestinationDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private List<String> parameterList = new ArrayList<>();
    private HotelDto hotelDto;

    public DestinationDto() {
        super();
    }

    public DestinationDto(List<String> parameterList) {
        super();
        this.parameterList = parameterList;
    }

    public DestinationDto(HotelDto hotelDto) {
        super();
        this.hotelDto = hotelDto;
    }

    public void addParameter(String parameter) {
        parameterList.add(parameter);
    }

}
