package by.kanarski.booking.entities.roomType;

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
public class RoomTypeTranslation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roomTypeTranslationId;
    private String roomTypeName;
    private RoomType roomType;
    private Language language;


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getRoomTypeTranslationId() {
        return roomTypeTranslationId;
    }

    public void setRoomTypeTranslationId(Long roomTypeTranslationId) {
        this.roomTypeTranslationId = roomTypeTranslationId;
    }

    @Column
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @ManyToOne
    @JoinColumn(
            name = "ROOM_TYPE_ID"
//            foreignKey = @ForeignKey(name = "ROOM_TYPE_TRANSLATIONS")
    )
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID"
//            foreignKey = @ForeignKey(name = "ROOM_TYPE_LANGUAGES")
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

        RoomTypeTranslation that = (RoomTypeTranslation) o;

        if (!roomTypeTranslationId.equals(that.roomTypeTranslationId)) return false;
        if (!roomTypeName.equals(that.roomTypeName)) return false;
        if (!roomType.equals(that.roomType)) return false;
        return language.equals(that.language);

    }

    @Override
    public int hashCode() {
        int result = roomTypeTranslationId.hashCode();
        result = 31 * result + roomTypeName.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
