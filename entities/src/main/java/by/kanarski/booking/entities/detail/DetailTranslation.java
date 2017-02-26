package by.kanarski.booking.entities.detail;

import by.kanarski.booking.entities.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class DetailTranslation implements Serializable {

    private static final long serialVersionUID = 852923229039242359L;
    private DetailLanguagePK detailLanguagePK;
    private String detailName;
    private Detail detail;
    private Language language;

    @EmbeddedId
    public DetailLanguagePK getDetailLanguagePK() {
        return detailLanguagePK;
    }

    public void setDetailLanguagePK(DetailLanguagePK detailLanguagePK) {
        this.detailLanguagePK = detailLanguagePK;
    }

    @Column(
            nullable = false
    )
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String facilityName) {
        this.detailName = facilityName;
    }

    @MapsId("DETAIL_ID")
    @ManyToOne
    @JoinColumn(
            name = "DETAIL_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_DEATAIL_TRANSLATION_DETAIL")
    )
    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    @MapsId("LANGUAGE_ID")
    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_DETAIL_LANGUAGE")
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

        DetailTranslation that = (DetailTranslation) o;

        if (!detailLanguagePK.equals(that.detailLanguagePK)) return false;
        if (!detailName.equals(that.detailName)) return false;
        if (!detail.equals(that.detail)) return false;
        return language.equals(that.language);

    }

    @Override
    public int hashCode() {
        int result = detailLanguagePK.hashCode();
        result = 31 * result + detailName.hashCode();
        result = 31 * result + detail.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
