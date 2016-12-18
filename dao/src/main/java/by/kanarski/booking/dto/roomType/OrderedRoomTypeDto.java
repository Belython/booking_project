package by.kanarski.booking.dto.roomType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedRoomTypeDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long roomTypeId;
    private Integer amount;

}
