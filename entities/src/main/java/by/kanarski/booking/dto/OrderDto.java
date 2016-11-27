package by.kanarski.booking.dto;

import by.kanarski.booking.dto.hotel.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UserDto user;
    private HotelDto hotel;
    private Integer totalPersons;
    private String checkInDate;
    private String checkOutDate;

}
