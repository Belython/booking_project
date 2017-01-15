package by.kanarski.booking.dto.location;

import by.kanarski.booking.constants.StateValue;
import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LocationDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public LocationDto(String country, String city) {
        super();
        this.country = country;
        this.city = city;
        this.locationStatus = StateValue.STATUS_ACTIVE;
    }

    public LocationDto() {
        super();
    }
}
