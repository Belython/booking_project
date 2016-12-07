package by.kanarski.booking.dto;

import by.kanarski.booking.managers.DatabaseManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
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
