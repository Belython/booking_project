package by.kanarski.booking.dto.roomType;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.Data;

@Data
public class RoomTypeDto extends LocalizableDto {

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private String facilities;
    private String roomTypeStatus;

    public RoomTypeDto(Long roomTypeId, String roomTypeName, Integer maxPersons, Double pricePerNight,
                       String facilities, String roomTypeStatus) {
        super();
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.facilities = facilities;
        this.roomTypeStatus = roomTypeStatus;
    }

    public RoomTypeDto() {
        super();
    }
}