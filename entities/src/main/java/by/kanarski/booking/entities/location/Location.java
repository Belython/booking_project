package by.kanarski.booking.entities.location;

import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.hotel.Hotel;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public class Location implements Serializable {

    private static final long serialVersionUID = -6439324165689937331L;
    private Long locationId;
    private String country;
    private String city;
    private Set<Hotel> hotelSet;
    private Map<Long, LocationTranslation> locationTranslationMap = new HashMap<>();
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
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Formula(Formulas.COUNTRY_FORMULA)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Formula(Formulas.CITY_FORMULA)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "location")
    public Set<Hotel> getHotelSet() {
        return hotelSet;
    }

    public void setHotelSet(Set<Hotel> hotelSet) {
        this.hotelSet = hotelSet;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "LOCATION_ID"
    )
    @MapKeyColumn(
            name = "LANGUAGE_ID"
    )
    public Map<Long, LocationTranslation> getLocationTranslationMap() {
        return locationTranslationMap;
    }

    public void setLocationTranslationMap(Map<Long, LocationTranslation> locationTranslationList) {
        this.locationTranslationMap = locationTranslationList;
    }

    @ManyToOne
    @JoinColumn(
            name = "STATUS_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_LOCATION_STATUS")
    )
    public State getStatus() {
        return status;
    }

    public void setStatus(State locationStatus) {
        this.status = locationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!locationId.equals(location.locationId)) return false;
        return status.equals(location.status);

    }

    @Override
    public int hashCode() {
        int result = locationId.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
