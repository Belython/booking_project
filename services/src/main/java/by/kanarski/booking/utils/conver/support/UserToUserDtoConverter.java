package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class UserToUserDtoConverter extends EntityConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        Long userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String login = user.getUserName();
        String password = user.getPassword();
        Set<String> roleSet = new HashSet<>();
        String userStatus = user.getStatus().getStateName();
        return new UserDto(userId, firstName, lastName, email, login, password, roleSet, userStatus);
    }
}
