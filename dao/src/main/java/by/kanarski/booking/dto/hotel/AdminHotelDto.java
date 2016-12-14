package by.kanarski.booking.dto.hotel;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminHotelDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;

    public AdminHotelDto(LocationDto location, String hotelName, String hotelStatus) {
        super();
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }
}
