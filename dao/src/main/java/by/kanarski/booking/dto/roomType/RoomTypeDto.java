package by.kanarski.booking.dto.roomType;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.facility.FacilityDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoomTypeDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private List<FacilityDto> facilityList;
    private String roomTypeStatus;

    public RoomTypeDto(Long roomTypeId, String roomTypeName, Integer maxPersons, Double pricePerNight,
                      List<FacilityDto> facilityList, String roomTypeStatus) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.facilityList = facilityList;
        this.roomTypeStatus = roomTypeStatus;
    }

    public RoomTypeDto() {
        super();
    }
}
