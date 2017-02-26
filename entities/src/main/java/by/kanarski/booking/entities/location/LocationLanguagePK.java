package by.kanarski.booking.entities.location;

import by.kanarski.booking.entities.abstr.LanguagePK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Embeddable
class LocationLanguagePK extends LanguagePK implements Serializable {

    private Long locationlId;

    @Column(name = "LOCATION_ID")
    public Long getLocationlId() {
        return locationlId;
    }

    public void setLocationlId(Long hotelId) {
        this.locationlId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LocationLanguagePK that = (LocationLanguagePK) o;

        return locationlId.equals(that.locationlId);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + locationlId.hashCode();
        return result;
    }
}
