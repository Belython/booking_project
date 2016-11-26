package by.kanarski.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long roomId;
    private HotelDto hotel;
    private RoomTypeDto roomType;
    private Integer roomNumber;
    private String roomStatus;

}
