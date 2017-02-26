package by.kanarski.booking.entities;

import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.io.Serializable;
import java.util.Set;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {

    private static final long serialVersionUID = -6949160304903788838L;
    private Long roomId;
    private Hotel hotel;
    private RoomType roomType;
    private Integer roomNumber;
    private Set<Bill> billSet;
    private State status;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    @Column(
            unique = true,
            nullable = false
    )
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @ManyToOne
    @JoinColumn(
            name = "HOTEL_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_HOTEL")
    )
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToOne
    @JoinColumn(
            name = "ROOM_TYPE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_ROOM_TYPE")
    )
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Column(
            nullable = false
    )
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

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_STATUS")
    )
    public State getStatus() {
        return status;
    }

    public void setStatus(State roomStatus) {
        this.status = roomStatus;
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
        return status.equals(room.status);
    }

    @Override
    public int hashCode() {
        Integer result = roomId.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + roomNumber.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
