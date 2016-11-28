package by.kanarski.booking.dto.location;

import by.kanarski.booking.constants.FieldValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public LocationDto(String country, String city) {
        this.country = country;
        this.city = city;
        this.locationStatus = FieldValue.STATUS_ACTIVE;
    }
}
