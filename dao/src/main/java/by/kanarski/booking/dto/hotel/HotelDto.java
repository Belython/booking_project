package by.kanarski.booking.dto.hotel;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelDto extends LocalizableDto {

    private Long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;

    public HotelDto(Long hotelId, LocationDto location, String hotelName) {
        super();
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto(LocationDto location, String hotelName) {
        super();
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto(String coutry, String city, String hotelName) {
        super();
        this.location = new LocationDto(coutry, city);
        this.hotelName = hotelName;
        this.hotelStatus = FieldValue.STATUS_ACTIVE;
    }

    public HotelDto() {
        super();
    }
}