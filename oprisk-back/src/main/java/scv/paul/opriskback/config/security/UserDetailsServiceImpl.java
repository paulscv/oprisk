package scv.paul.opriskback.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scv.paul.opriskback.dao.UserDAO;
import scv.paul.opriskback.entity.User;

/**
 * Реализация UserDetailsService для работы SpringSecurity
 *
 * @author Rostislav Dublin
 * @since 2017-03-01
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        User user = userDAO.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                userDAO.getAuthorities(user)
        );
    }

}