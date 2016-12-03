package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends LocalizableDto {

    private UserDto user;
    private HotelDto hotel;
    private Integer totalPersons;
    private Integer totalRooms;
    private String checkInDate;
    private String checkOutDate;



}
