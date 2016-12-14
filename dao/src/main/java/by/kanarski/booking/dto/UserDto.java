package by.kanarski.booking.dto;

import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.RegExp;
import by.kanarski.booking.constants.ValidationMessage;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UserDto extends User implements Serializable {

    private static final Long serialVersionUID = 1L;

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
        super(login, password, getGrantedAuthorities(role));
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public UserDto(Long userId, String firstName, String lastName, String email, String login, String password, String role, String userStatus) {
        super(login, password, getGrantedAuthorities(role));
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public UserDto() {
        super("blank", "blank", getGrantedAuthorities(FieldValue.ROLE_ANONYMOUS));
    }

    private static List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }


}
