package by.kanarski.booking.entities.facility;

import by.kanarski.booking.entities.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import javax.persistence.Entity;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(
            unique = true,
            nullable = false
    )
    public Long getFactilityTranslationId() {
        return factilityTranslationId;
    }

    public void setFactilityTranslationId(Long factilityTranslationId) {
        this.factilityTranslationId = factilityTranslationId;
    }

    @Column(
            nullable = false
    )
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
            name = "LANGUAGE_ID"
    )
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
