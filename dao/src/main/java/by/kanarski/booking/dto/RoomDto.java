package by.kanarski.booking.dto;

import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roomId;
    private HotelDto hotel;
    private RoomTypeDto roomType;
    private Integer roomNumber;
    private String roomStatus;

}
