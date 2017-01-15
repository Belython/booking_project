package by.kanarski.booking.services.impl;

import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDto userDto = null;
        try {
            userDto = userService.getByLogin(userName);
            if (userDto == null) {
                throw new UsernameNotFoundException("Username not found");
            }
        } catch (ServiceException e) {
            BookingExceptionHandler.handleServiceException(e);
        }
        return userDto;
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<String> roleSet = userDto.getRoleSet();
        for (String role : roleSet) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
