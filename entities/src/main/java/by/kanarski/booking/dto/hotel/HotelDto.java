package by.kanarski.booking.dto.hotel;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dto.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {

    private Long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;

    public HotelDto(Long hotelId, LocationDto location, String hotelName) {
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto(LocationDto location, String hotelName) {
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }
}
