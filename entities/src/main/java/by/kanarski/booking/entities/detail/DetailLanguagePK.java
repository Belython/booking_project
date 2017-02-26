package by.kanarski.booking.entities.detail;

import by.kanarski.booking.entities.abstr.LanguagePK;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Embeddable
class DetailLanguagePK extends LanguagePK implements Serializable {

    private static final long serialVersionUID = 4269849056713674083L;
    private Long detaillId;

    @Column(name = "DETAIL_ID")
    public Long getDetaillId() {
        return detaillId;
    }

    public void setDetaillId(Long hotelId) {
        this.detaillId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DetailLanguagePK that = (DetailLanguagePK) o;

        return detaillId.equals(that.detaillId);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + detaillId.hashCode();
        return result;
    }
}
