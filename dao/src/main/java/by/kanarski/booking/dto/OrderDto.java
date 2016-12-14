package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private UserDto user;
    private HotelDto hotel;
    private UserHotelDto userHotelDto;
    private RoomTypeDto roomType;
    private Integer totalPersons;
    private Integer totalRooms;
    private String checkInDate;
    private String checkOutDate;
    private boolean isUpdated;

}
