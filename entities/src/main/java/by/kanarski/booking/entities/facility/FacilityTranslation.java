package by.kanarski.booking.entities.facility;

import by.kanarski.booking.entities.Language;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class FacilityTranslation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long factilityTranslationId;
    private String facilityName;
    private Facility facility;
    private Language language;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getFactilityTranslationId() {
        return factilityTranslationId;
    }

    public void setFactilityTranslationId(Long factilityTranslationId) {
        this.factilityTranslationId = factilityTranslationId;
    }

    @Column
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @ManyToOne
    @JoinColumn(name = "FACILITY_ID")
    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            foreignKey = @ForeignKey(name = "FACILITY_LANGUAGES")
    )
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
