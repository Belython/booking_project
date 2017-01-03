package by.kanarski.booking.dto;

import by.kanarski.booking.annotations.TargetEntity;
import by.kanarski.booking.constants.RegExp;
import by.kanarski.booking.constants.StateValue;
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
import java.util.Set;

@Data
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
    private Set<String> roleSet;
    private String userStatus;

    public UserDto(String firstName, String lastName, String email, String login, String password,
                   Set<String> roleSet, String userStatus) {
        super(login, password, getGrantedAuthorities(roleSet));
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roleSet = roleSet;
        this.userStatus = userStatus;
    }

    public UserDto(Long userId, String firstName, String lastName, String email, String login, String password,
                   Set<String> roleSet, String userStatus) {
        super(login, password, getGrantedAuthorities(roleSet));
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roleSet = roleSet;
        this.userStatus = userStatus;
    }

    public UserDto() {
        super("blank", "blank", getAnnonymous());
    }

    private static List<GrantedAuthority> getGrantedAuthorities(Set<String> roleSet) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roleSet) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }
        return authorities;
    }

    private static List<GrantedAuthority> getAnnonymous() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StateValue.ROLE_ANONYMOUS));
        return authorities;
    }


}
