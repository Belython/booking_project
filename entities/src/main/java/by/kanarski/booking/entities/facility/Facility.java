package by.kanarski.booking.entities.facility;

import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.utils.Formulas;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long facilityId;
    private String facilityName;
    private Map<Long, FacilityTranslation> facilityTranslationMap;
    private Set<RoomType> roomTypeSet;
    private String facilityStatus;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    @Formula(Formulas.FACILITY_NAME_FORMULA)
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "FACILITY_ID"
//            foreignKey = @ForeignKey(name = "FACILITY_TRANSLATIONS")
    )
    @MapKeyColumn(name = "LANGUAGE_ID")
    public Map<Long, FacilityTranslation> getFacilityTranslationMap() {
        return facilityTranslationMap;
    }

    public void setFacilityTranslationMap(Map<Long, FacilityTranslation> facilityTranslationMap) {
        this.facilityTranslationMap = facilityTranslationMap;
    }

    @ManyToMany(mappedBy = "facilitySet")
    public Set<RoomType> getRoomTypeSet() {
        return roomTypeSet;
    }

    public void setRoomTypeSet(Set<RoomType> roomTypeSet) {
        this.roomTypeSet = roomTypeSet;
    }

    @Column
    public String getFacilityStatus() {
        return facilityStatus;
    }

    public void setFacilityStatus(String facilityStatus) {
        this.facilityStatus = facilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (!facilityId.equals(facility.facilityId)) return false;
        return facilityStatus.equals(facility.facilityStatus);

    }

    @Override
    public int hashCode() {
        int result = facilityId.hashCode();
        result = 31 * result + facilityStatus.hashCode();
        return result;
    }
}
