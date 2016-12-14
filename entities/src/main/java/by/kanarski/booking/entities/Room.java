package by.kanarski.booking.entities;

import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@DynamicUpdate
@DynamicInsert
public class Room implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roomId;
    private Hotel hotel;
    private RoomType roomType;
    private Integer roomNumber;
    private Set<Bill> billSet;
    private String roomStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @ManyToOne
    @JoinColumn(
            name = "HOTEL_ID"
//            foreignKey = @ForeignKey(name = "HOTEL_ROOMS")
    )
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToOne
    @JoinColumn(
            name = "ROOM_TYPE_ID"
//            foreignKey = @ForeignKey(name = "ROOM_TYPE_ROOMS")
    )
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Column
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @ManyToMany(mappedBy = "roomSet")
    public Set<Bill> getBillSet() {
        return billSet;
    }

    public void setBillSet(Set<Bill> billSet) {
        this.billSet = billSet;
    }

    @Column
    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!roomId.equals(room.roomId)) return false;
        if (!hotel.equals(room.hotel)) return false;
        if (!roomType.equals(room.roomType)) return false;
        if (!roomNumber.equals(room.roomNumber)) return false;
        return roomStatus.equals(room.roomStatus);

    }

    @Override
    public int hashCode() {
        Integer result = roomId.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber.hashCode();
        result = 31 * result + roomStatus.hashCode();
        return result;
    }
}
