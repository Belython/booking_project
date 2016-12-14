package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.utils.HotelUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long billId;
    private String bookingDate;
    private UserDto client;
    private Integer totalPersons;
    private String checkInDate;
    private String checkOutDate;
    private HotelDto hotel;
    private Map<RoomTypeDto, Integer> roomTypeMap;
    private List<RoomDto> roomList;
    private Double paymentAmount;
    private String billStatus;

    public BillDto(Long billId, UserDto client, Integer totalPersons, String checkInDate,
                   String checkOutDate, HotelDto hotel, List<RoomDto> roomList,
                   Double paymentAmount, String billStatus) {
        this.billId = billId;
        this.client = client;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotel = hotel;
        this.roomTypeMap = HotelUtil.countRoomTypeDto(roomList);
        this.roomList = roomList;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(UserDto client, Integer totalPersons, String checkInDate,
                   String checkOutDate, HotelDto hotel, List<RoomDto> roomList,
                   Double paymentAmount) {
        this.client = client;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotel = hotel;
        this.roomTypeMap = HotelUtil.countRoomTypeDto(roomList);
        this.roomList = roomList;
        this.paymentAmount = paymentAmount;
        this.billStatus = FieldValue.STATUS_NOT_PAID;
    }

    public BillDto(Long billId, UserDto client, Integer totalPersons, String checkInDate,
                   String checkOutDate, HotelDto hotel, Map<RoomTypeDto, Integer> roomTypeMap,
                   Double paymentAmount, String billStatus) {
        this.billId = billId;
        this.client = client;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotel = hotel;
        this.roomTypeMap = roomTypeMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = billStatus;
    }

    public BillDto(UserDto client, Integer totalPersons, String checkInDate, String checkOutDate,
                   HotelDto hotel, Map<RoomTypeDto, Integer> roomTypeMap, Double paymentAmount) {
        this.billId = FieldValue.UNDEFINED_ID;
        this.client = client;
        this.totalPersons = totalPersons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotel = hotel;
        this.roomTypeMap = roomTypeMap;
        this.paymentAmount = paymentAmount;
        this.billStatus = FieldValue.STATUS_NOT_PAID;
    }

    public void setRoomList(List<RoomDto> roomList) {
        this.roomTypeMap = HotelUtil.countRoomTypeDto(roomList);
        this.roomList = roomList;
    }
}
