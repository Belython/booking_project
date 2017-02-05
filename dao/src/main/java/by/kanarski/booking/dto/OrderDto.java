package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter()
public class OrderDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String destination;
    private UserDto user;
    private HotelDto hotel;
    private UserHotelDto userHotelDto;
    private RoomTypeDto roomType;
    private List<String> facilityList;
    private String roomFacilities;
    private Integer totalPersons;
    private Integer totalRooms;
    private String checkInDate;
    private String checkOutDate;
    private String sortPrice;

    public void setDestination(String destination) {
        this.destination = destination;
        this.hotel = DestinationDto.getHotelDto(destination);
    }

    public void setFacilityList(List<String> facilityList) {
        this.facilityList = facilityList;
        if (roomType == null) {
            roomType = new RoomTypeDto();
        }
        List<FacilityDto> facilityDtoList = new ArrayList<>();
        for (String facilityAsString : facilityList) {
            FacilityDto facilityDto = new FacilityDto();
            facilityDto.setFacilityName(facilityAsString);
            facilityDtoList.add(facilityDto);
        }
        roomType.setFacilityList(facilityDtoList);
    }

}
