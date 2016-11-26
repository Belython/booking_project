package by.kanarski.booking.entities.roomType;

import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.utils.Formulas;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private Set<Facility> facilitySet = new TreeSet<>();
    private Map<Long, RoomTypeTranslation> roomTypeTranslationMap = new HashMap<>();
    private String roomTypeStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Formula(Formulas.ROOM_TYPE_NAME_FORMULA)
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @Column
    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    @Column
    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ROOM_TYPE_FACILITIES",
            joinColumns = @JoinColumn(name = "ROOM_TYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FACILITY_ID")
    )
    public Set<Facility> getFacilitySet() {
        return facilitySet;
    }

    public void setFacilitySet(Set<Facility> facilitySet) {
        this.facilitySet = facilitySet;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_TYPE_ID")
    @MapKeyColumn(name = "LANGUAGE_ID")
    public Map<Long, RoomTypeTranslation> getRoomTypeTranslationMap() {
        return roomTypeTranslationMap;
    }

    public void setRoomTypeTranslationMap(Map<Long, RoomTypeTranslation> roomTypeTranslationMap) {
        this.roomTypeTranslationMap = roomTypeTranslationMap;
    }

    @Column
    public String getRoomTypeStatus() {
        return roomTypeStatus;
    }

    public void setRoomTypeStatus(String roomTypeStatus) {
        this.roomTypeStatus = roomTypeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (!roomTypeId.equals(roomType.roomTypeId)) return false;
        if (!maxPersons.equals(roomType.maxPersons)) return false;
        if (!pricePerNight.equals(roomType.pricePerNight)) return false;
        if (!facilitySet.equals(roomType.facilitySet)) return false;
        if (!roomTypeTranslationMap.equals(roomType.roomTypeTranslationMap)) return false;
        return roomTypeStatus.equals(roomType.roomTypeStatus);

    }

    @Override
    public int hashCode() {
        int result = roomTypeId.hashCode();
        result = 31 * result + maxPersons.hashCode();
        result = 31 * result + pricePerNight.hashCode();
        result = 31 * result + facilitySet.hashCode();
        result = 31 * result + roomTypeTranslationMap.hashCode();
        result = 31 * result + roomTypeStatus.hashCode();
        return result;
    }
}
