package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.User;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class UserDtoToUserConverter extends EntityConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        Long userId = userDto.getUserId();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String login = userDto.getUserName();
        String password = userDto.getPassword();
        Set<State> roleSet = getConversionService().convert(userDto.getRoleSet(), State.class);
        State userStatus = getConversionService().convert(userDto.getUserStatus(), State.class);
        return entityBuilder.buildUser(userId, firstName, lastName, email, login, password, roleSet, userStatus);
    }
}
