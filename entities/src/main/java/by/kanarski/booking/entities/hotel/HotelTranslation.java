package by.kanarski.booking.entities.hotel;

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
@AllArgsConstructor
@NoArgsConstructor
public class HotelTranslation implements Serializable {

    private static final long serialVersionUID = -403304878104377848L;
    private HotelLanguagePK hotelLanguagePK;
    private String hotelName;
    private Hotel hotel;
    private Language language;

    @EmbeddedId
    public HotelLanguagePK getHotelLanguagePK() {
        return hotelLanguagePK;
    }

    public void setHotelLanguagePK(HotelLanguagePK hotelLanguagePK) {
        this.hotelLanguagePK = hotelLanguagePK;
    }

    @Column(
            nullable = false
    )
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @MapsId("HOTEL_ID")
    @ManyToOne
    @JoinColumn(
            name = "HOTEL_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_HOTEL_TRANSLATION_HOTEL")
    )
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @MapsId("LANGUAGE_ID")
    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_HOTEL_TRANSLATAION_LANGUAGE")
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

        HotelTranslation that = (HotelTranslation) o;

        if (!hotelLanguagePK.equals(that.hotelLanguagePK)) return false;
        if (!hotelName.equals(that.hotelName)) return false;
        if (!hotel.equals(that.hotel)) return false;
        return language.equals(that.language);

    }

    @Override
    public int hashCode() {
        int result = hotelLanguagePK.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotel.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
