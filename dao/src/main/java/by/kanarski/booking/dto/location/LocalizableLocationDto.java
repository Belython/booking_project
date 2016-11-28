package by.kanarski.booking.dto.location;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizableLocationDto extends LocalizableDto {

    private Long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public LocalizableLocationDto(String country, String city, String locationStatus) {
        this.country = country;
        this.city = city;
        this.locationStatus = locationStatus;
    }
}
