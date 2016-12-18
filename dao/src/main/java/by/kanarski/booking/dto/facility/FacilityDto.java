package by.kanarski.booking.dto.facility;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDto extends LocalizableDto {

    private Long facilityId;
    private String facilityName;
    private String facilityStatus;

}
