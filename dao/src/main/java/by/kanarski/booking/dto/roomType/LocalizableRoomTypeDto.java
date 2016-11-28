package by.kanarski.booking.dto.roomType;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizableRoomTypeDto extends LocalizableDto {

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private String facilities;
    private String roomTypeStatus;

    public LocalizableRoomTypeDto(String roomTypeName, Integer maxPersons, Double pricePerNight,
                                  String facilities, String roomTypeStatus) {
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.facilities = facilities;
        this.roomTypeStatus = roomTypeStatus;
    }
}
