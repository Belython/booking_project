package by.kanarski.booking.entities.facility;

import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.utils.Formulas;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long facilityId;
    private String facilityName;
    private Map<Long, FacilityTranslation> facilityTranslationMap;
    private Set<RoomType> roomTypeSet;
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
    )
    @MapKeyColumn(
            name = "LANGUAGE_ID"
    )
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


    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false
    )
    public State getStatus() {
        return status;
    }

    public void setStatus(State facilityStatus) {
        this.status = facilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (!facilityId.equals(facility.facilityId)) return false;
        return status.equals(facility.status);

    }

    @Override
    public int hashCode() {
        int result = facilityId.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
