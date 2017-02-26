package by.kanarski.booking.entities.location;

import by.kanarski.booking.entities.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class LocationTranslation implements Serializable {

    private static final long serialVersionUID = 3592113648776981107L;
    private LocationLanguagePK locationLanguagePK;
    private String country;
    private String city;
    private Location location;
    private Language language;

    @EmbeddedId
    public LocationLanguagePK getLocationLanguagePK() {
        return locationLanguagePK;
    }

    public void setLocationLanguagePK(LocationLanguagePK locationLanguagePK) {
        this.locationLanguagePK = locationLanguagePK;
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

    @MapsId("LOCATION_ID")
    @ManyToOne
    @JoinColumn(
            name = "LOCATION_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_LOCATION_TRANSLATION_LOCATION")
    )
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @MapsId("LANGUAGE_ID")
    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_LOCATION_TRANSLATION_LANGUAGE")
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

        if (!locationLanguagePK.equals(that.locationLanguagePK)) return false;
        if (!country.equals(that.country)) return false;
        if (!city.equals(that.city)) return false;
        if (!location.equals(that.location)) return false;
        return language.equals(that.language);

    }

    @Override
    public int hashCode() {
        int result = locationLanguagePK.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
