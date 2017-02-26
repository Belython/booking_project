package by.kanarski.booking.entities.roomType;

import by.kanarski.booking.entities.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeTranslation implements Serializable {

    private static final long serialVersionUID = 6138512031597832416L;
    private RoomTypeLanguagePK roomTypeLanguagePK;
    private String roomTypeName;
    private RoomType roomType;
    private Language language;

    @EmbeddedId
    public RoomTypeLanguagePK getRoomTypeLanguagePK() {
        return roomTypeLanguagePK;
    }

    public void setRoomTypeLanguagePK(RoomTypeLanguagePK roomTypeLanguagePK) {
        this.roomTypeLanguagePK = roomTypeLanguagePK;
    }

    @Column(
            nullable = false
    )
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @MapsId("ROOM_TYPE_ID")
    @ManyToOne
    @JoinColumn(
            name = "ROOM_TYPE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_TYPE_TRANSLATION_ROOM_TYPE")
    )
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @MapsId("LANGUAGE_ID")
    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ROOM_TYPE_TRANSLATION_LANGUAGE")
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

        if (!roomTypeLanguagePK.equals(that.roomTypeLanguagePK)) return false;
        if (!roomTypeName.equals(that.roomTypeName)) return false;
        if (!roomType.equals(that.roomType)) return false;
        return language.equals(that.language);

    }

    @Override
    public int hashCode() {
        int result = roomTypeLanguagePK.hashCode();
        result = 31 * result + roomTypeName.hashCode();
        result = 31 * result + roomType.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
