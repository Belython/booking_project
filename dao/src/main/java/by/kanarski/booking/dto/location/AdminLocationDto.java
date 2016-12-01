package by.kanarski.booking.dto.location;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLocationDto extends LocalizableDto {

    private Long locationId;
    private String country;
    private String city;
    private String locationStatus;

    public AdminLocationDto(String country, String city, String locationStatus) {
        super();
        this.country = country;
        this.city = city;
        this.locationStatus = locationStatus;
    }
}
