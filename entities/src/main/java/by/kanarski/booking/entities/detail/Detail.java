package by.kanarski.booking.entities.detail;

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
import javax.persistence.ForeignKey;
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
public class Detail implements Serializable {

    private static final long serialVersionUID = -6727571532040199395L;
    private Long detailId;
    private String detailName;
    private Map<Long, DetailTranslation> detailTranslationMap;
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
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    @Formula(Formulas.DETAIL_NAME_FORMULA)
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "FACILITY_ID"
    )
    @MapKeyColumn(
            name = "LANGUAGE_ID"
    )
    public Map<Long, DetailTranslation> getDetailTranslationMap() {
        return detailTranslationMap;
    }

    public void setDetailTranslationMap(Map<Long, DetailTranslation> detailTranslationMap) {
        this.detailTranslationMap = detailTranslationMap;
    }

    @ManyToMany(mappedBy = "detailSet")
    public Set<RoomType> getRoomTypeSet() {
        return roomTypeSet;
    }

    public void setRoomTypeSet(Set<RoomType> roomTypeSet) {
        this.roomTypeSet = roomTypeSet;
    }

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_DETAIL_STATUS")
    )
    public State getStatus() {
        return status;
    }

    public void setStatus(State detailStatus) {
        this.status = detailStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        if (!detailId.equals(detail.detailId)) return false;
        if (!detailName.equals(detail.detailName)) return false;
        return status.equals(detail.status);

    }

    @Override
    public int hashCode() {
        int result = detailId.hashCode();
        result = 31 * result + detailName.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
