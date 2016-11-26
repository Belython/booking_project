package by.kanarski.booking.entities.location;

import by.kanarski.booking.entities.Language;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class LocationTranslation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long locationTransalteId;
    private String country;
    private String city;
    private Location location;
    private Language language;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getLocationTransalteId() {
        return locationTransalteId;
    }

    public void setLocationTransalteId(Long locationTransalteId) {
        this.locationTransalteId = locationTransalteId;
    }

    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(
            name = "LOCATION_ID",
            foreignKey = @ForeignKey(name = "LOCATION_TRANSLATIONS")
    )
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            foreignKey = @ForeignKey(name = "LOCATION_LANGUAGES")
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

        if (!locationTransalteId.equals(that.locationTransalteId)) return false;
        if (!country.equals(that.country)) return false;
        if (!city.equals(that.city)) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = locationTransalteId.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
