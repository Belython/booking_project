package by.kanarski.booking.dto.hotel;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.abstr.LocalizableDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.utils.HotelUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHotelDto extends LocalizableDto {

    private Long hotelId;
    private LocationDto location;
    private String hotelName;
    private String hotelStatus;
    private List<RoomDto> roomList;
    private Map<RoomTypeDto, Integer> roomTypesCount;
    private Integer roomsAvailable;

    public UserHotelDto(Long hotelId, String country, String city, String hotelName, String hotelStatus) {
        super();
        this.hotelId = hotelId;
        this.location = new LocationDto(country, city);
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public UserHotelDto(Long hotelId, LocationDto location, String hotelName, String hotelStatus) {
        super();
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.hotelStatus = hotelStatus;
    }

    public UserHotelDto(Long hotelId, LocationDto location, String hotelName, List<RoomDto> roomList) {
        super();
        this.hotelId = hotelId;
        this.location = location;
        this.hotelName = hotelName;
        this.roomList = roomList;
        this.roomsAvailable = roomList.size();
        this.roomTypesCount = HotelUtil.countRoomTypeDto(roomList);
    }

    public UserHotelDto(HotelDto hotel, List<RoomDto> roomList) {
        super();
        this.hotelId = hotel.getHotelId();
        this.location = hotel.getLocation();
        this.hotelName = hotel.getHotelName();
        this.hotelStatus = hotel.getHotelStatus();
        this.roomList = roomList;
        this.roomTypesCount = HotelUtil.countRoomTypeDto(this.roomList);
        this.roomsAvailable = this.roomList.size();
    }

    public List<RoomDto> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomDto> roomList) {
        this.roomList = roomList;
        this.roomTypesCount = HotelUtil.countRoomTypeDto(roomList);
        this.roomsAvailable = roomList.size();
    }
}
