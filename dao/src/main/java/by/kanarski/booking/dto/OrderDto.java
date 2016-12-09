package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends LocalizableDto {

    private UserDto user;
    private HotelDto hotel;
    private RoomTypeDto roomType;
    private Integer totalPersons;
    private Integer totalRooms;
    private String checkInDate;
    private String checkOutDate;
    private Integer page;
    private Integer perPage;
    private Integer pages;
    private boolean isUpdated;



}
