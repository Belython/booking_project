package by.kanarski.booking.dto.roomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDto {

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private String facilities;
    private String roomTypeStatus;
}
