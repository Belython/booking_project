package by.kanarski.booking.dto;

import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private User user;
    private Hotel hotel;
    private Integer totalPersons;
    private Integer totalRooms;
    private Long checkInDate;
    private Long checkOutDate;

}