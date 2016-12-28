package by.kanarski.booking.entities.hotel;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.utils.Formulas;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Hotel implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long hotelId;
    private String hotelName;
    private Location location;
    private Set<Room> roomSet;
    private Map<Long, HotelTranslation> hotelTranslationMap;
    private State hotelStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    @Column(
            unique = true,
            nullable = false
    )
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Formula(Formulas.HOTEL_NAME_FORMULA)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @ManyToOne
    @JoinColumn(
            name = "LOCATION_ID"
    )
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "hotel")
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "HOTEL_ID"
    )
    @MapKeyColumn(name = "LANGUAGE_ID")
    public Map<Long, HotelTranslation> getHotelTranslationMap() {
        return hotelTranslationMap;
    }

    public void setHotelTranslationMap(Map<Long, HotelTranslation> hotelTranslationMap) {
        this.hotelTranslationMap = hotelTranslationMap;
    }

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false
    )
    public State getHotelStatus() {
        return hotelStatus;
    }

    public void setHotelStatus(State hotelStatus) {
        this.hotelStatus = hotelStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (!hotelId.equals(hotel.hotelId)) return false;
        if (!location.equals(hotel.location)) return false;
        return hotelStatus.equals(hotel.hotelStatus);

    }

    @Override
    public int hashCode() {
        int result = hotelId.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + hotelStatus.hashCode();
        return result;
    }
}
