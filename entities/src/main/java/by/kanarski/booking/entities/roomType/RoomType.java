package by.kanarski.booking.entities.roomType;

import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.detail.Detail;
import by.kanarski.booking.utils.Formulas;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.io.Serializable;
import java.util.*;

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
public class RoomType implements Serializable {

    private static final long serialVersionUID = -6297931160646136062L;
    private Long roomTypeId;
    private String roomTypeName;
    private Integer maxPersons;
    private Double pricePerNight;
    private Set<Detail> detailSet = new TreeSet<>();
    private Set<Room> roomSet = new HashSet<>();
    private Map<Long, RoomTypeTranslation> roomTypeTranslationMap = new HashMap<>();
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

    @Column(
            nullable = false
    )
    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    @Column(
            nullable = false
    )
    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ROOM_TYPE_DETAILS",
            joinColumns = @JoinColumn(
                    name = "ROOM_TYPE_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(name = "FK_ROOM_TYPE_DETAILS")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "DETAIL_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(name = "FK_DETAILS_ROOM_TYPE")
            )
    )
    public Set<Detail> getDetailSet() {
        return detailSet;
    }

    public void setDetailSet(Set<Detail> detailSet) {
        this.detailSet = detailSet;
    }

    @OneToMany(mappedBy = "roomType")
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ROOM_TYPE_ID"
    )
    @MapKeyColumn(
            name = "LANGUAGE_ID"
    )
    public Map<Long, RoomTypeTranslation> getRoomTypeTranslationMap() {
        return roomTypeTranslationMap;
    }

    public void setRoomTypeTranslationMap(Map<Long, RoomTypeTranslation> roomTypeTranslationMap) {
        this.roomTypeTranslationMap = roomTypeTranslationMap;
    }

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_TYPE_STATUS")
    )
    public State getStatus() {
        return status;
    }

    public void setStatus(State roomTypeStatus) {
        this.status = roomTypeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;

        if (!roomTypeId.equals(roomType.roomTypeId)) return false;
        if (!maxPersons.equals(roomType.maxPersons)) return false;
        if (!pricePerNight.equals(roomType.pricePerNight)) return false;
        if (!detailSet.equals(roomType.detailSet)) return false;
        return status.equals(roomType.status);

    }

    @Override
    public int hashCode() {
        int result = roomTypeId.hashCode();
        result = 31 * result + maxPersons.hashCode();
        result = 31 * result + pricePerNight.hashCode();
        result = 31 * result + detailSet.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
