package by.kanarski.booking.entities.hotel;

import by.kanarski.booking.entities.Language;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
public class HotelTranslation implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long hotelTranslationId;
    private String hotelName;
    private Hotel hotel;
    private Language language;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getHotelTranslationId() {
        return hotelTranslationId;
    }

    public void setHotelTranslationId(Long hotelTranslationId) {
        this.hotelTranslationId = hotelTranslationId;
    }

    @Column
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @ManyToOne
    @JoinColumn(
            name = "LOCATION_ID"
//            foreignKey = @ForeignKey(name = "LOCATION_TRANSLATIONS")
    )
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID"
//            foreignKey = @ForeignKey(name = "LOCATION_LANGUAGES")
    )
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


}
