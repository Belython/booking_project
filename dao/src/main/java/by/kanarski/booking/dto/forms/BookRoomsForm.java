package by.kanarski.booking.dto.forms;

import by.kanarski.booking.dto.roomType.OrderedRoomTypesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRoomsForm {

    private List<OrderedRoomTypesDto> orderedRooms;

}
