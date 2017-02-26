package by.kanarski.booking.entities.abstr;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@MappedSuperclass
public abstract class LanguagePK {

    private Long languageId;

    @Column(name = "LANGUAGE_ID")
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagePK that = (LanguagePK) o;

        return languageId.equals(that.languageId);

    }

    @Override
    public int hashCode() {
        return languageId.hashCode();
    }
}
