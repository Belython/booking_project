package by.kanarski.booking.dto.roomType;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AdminRoomTypeDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private String facilities;
    private String roomTypeStatus;

    public AdminRoomTypeDto(String roomTypeName, Integer maxPersons, Double pricePerNight,
                            String facilities, String roomTypeStatus) {
        super();
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.facilities = facilities;
        this.roomTypeStatus = roomTypeStatus;
    }

    public AdminRoomTypeDto() {
        super();
    }
}
