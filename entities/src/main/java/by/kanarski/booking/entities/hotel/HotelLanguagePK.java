package by.kanarski.booking.entities.hotel;

import by.kanarski.booking.entities.abstr.LanguagePK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Embeddable
class HotelLanguagePK extends LanguagePK implements Serializable {

    private static final long serialVersionUID = -254386227525378352L;
    private Long hotelId;

    @Column(name = "HOTEL_ID")
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HotelLanguagePK that = (HotelLanguagePK) o;

        return hotelId.equals(that.hotelId);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + hotelId.hashCode();
        return result;
    }
}
