package by.kanarski.booking.dto;

import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private User user;
    private Hotel hotel;
    private RoomType roomType;
    private Integer totalPersons;
    private Integer totalRooms;
    private Long checkInDate;
    private Long checkOutDate;

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", hotel=" + hotel +
                ", roomType=" + roomType +
                ", totalPersons=" + totalPersons +
                ", totalRooms=" + totalRooms +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
