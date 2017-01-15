package by.kanarski.booking.entities.location;

import by.kanarski.booking.entities.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class LocationTranslation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long locationTranslationId;
    private String country;
    private String city;
    private Location location;
    private Language language;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    @Column(
            unique = true,
            nullable = false
    )
    public Long getLocationTranslationId() {
        return locationTranslationId;
    }

    public void setLocationTranslationId(Long locationTransalteId) {
        this.locationTranslationId = locationTransalteId;
    }

    @Column(
            nullable = false
    )
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(
            nullable = false
    )
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID"
    )
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationTranslation that = (LocationTranslation) o;

        if (!locationTranslationId.equals(that.locationTranslationId)) return false;
        if (!country.equals(that.country)) return false;
        if (!city.equals(that.city)) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = locationTranslationId.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
