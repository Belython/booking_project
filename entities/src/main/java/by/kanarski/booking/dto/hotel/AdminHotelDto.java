package by.kanarski.booking.dto.hotel;

import by.kanarski.booking.dto.abstr.AdminDto;
import by.kanarski.booking.dto.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminHotelDto extends AdminDto {

    private Long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;

    public AdminHotelDto(LocationDto location, String hotelName, String hotelStatus) {
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }
}
