package by.kanarski.booking.entities.roomType;

import by.kanarski.booking.entities.abstr.LanguagePK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Embeddable
class RoomTypeLanguagePK extends LanguagePK implements Serializable {

    private static final long serialVersionUID = -6564880560582152453L;
    private Long roomTypelId;

    @Column(name = "ROOM_TYPE_ID")
    public Long getRoomTypelId() {
        return roomTypelId;
    }

    public void setRoomTypelId(Long hotelId) {
        this.roomTypelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoomTypeLanguagePK that = (RoomTypeLanguagePK) o;

        return roomTypelId.equals(that.roomTypelId);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + roomTypelId.hashCode();
        return result;
    }
}
