package by.kanarski.booking.dto;

import by.kanarski.booking.constants.ValidationMessage;
import by.kanarski.booking.constants.RegExp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;

    @Pattern(regexp = RegExp.NAME, message = ValidationMessage.NAME_PATTERN)
    @NotNull(message = ValidationMessage.EMPTY_FIELD)
    private String firstName;

    @Pattern(regexp = RegExp.NAME, message = ValidationMessage.NAME_PATTERN)
    @NotNull(message = ValidationMessage.EMPTY_FIELD)
    private String lastName;

    @Pattern(regexp = RegExp.EMAIL, message = ValidationMessage.EMAIL_PATTERN)
    @NotNull(message = ValidationMessage.EMPTY_FIELD)
    private String email;

    @Pattern(regexp = RegExp.LOGIN, message = ValidationMessage.LOGIN_PATTERN)
    @NotNull(message = ValidationMessage.EMPTY_FIELD)
    private String login;

    @Pattern(regexp = RegExp.PASSWORD, message = ValidationMessage.PASSWROD_PATTERN)
    @NotNull(message = ValidationMessage.EMPTY_FIELD)
    private String password;
    private String role;
    private String userStatus;

    public UserDto(String firstName, String lastName, String email, String login, String password, String role, String userStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }
}
