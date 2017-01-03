package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HotelToHotelDtoConverter implements Converter<Hotel, HotelDto> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param user the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public UserDto convert(User user) {
        Long userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String login = user.getUserName();
        String password = user.getPassword();
        Set<String> roleSet = new HashSet<>();
        String userStatus = user.getUserStatus().getStateName();
        return new UserDto(userId, firstName, lastName, email, login, password, roleSet, userStatus);
    }
}
