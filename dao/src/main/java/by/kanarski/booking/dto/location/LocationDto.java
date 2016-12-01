package by.kanarski.booking.dto.location;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LocationDto extends LocalizableDto {

    private Long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public LocationDto(String country, String city) {
        super();
        this.country = country;
        this.city = city;
        this.locationStatus = FieldValue.STATUS_ACTIVE;
    }

    public LocationDto() {
        super();
    }
}
