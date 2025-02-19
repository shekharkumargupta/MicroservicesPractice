package com.skcodify.auth.services;

import com.skcodify.auth.domain.AppUser;
import com.sun.istack.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MySqlUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(MySqlUserDetailsService.class);

    private static Map<String, AppUser> users;

    public MySqlUserDetailsService() {
        log.info("Initializing MySqlUserDetailsService...");
        users = new HashMap<>();
        AppUser appUser = new AppUser();
        appUser.setUsername("user");
        appUser.setPassword("user");
        appUser.setEnabled(true);
        appUser.getRoles().add("USER");

        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEnabled(true);
        admin.getRoles().add("ADMIN");

        users.put(appUser.getUsername(), appUser);
        users.put(admin.getUsername(), admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = users.get(username);
        if(appUser == null){
            throw new UsernameNotFoundException("Invalid User: " + username);
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        appUser.getRoles().forEach(s -> {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + s));
        });

        User userDetails = new User(appUser.getUsername(),
                appUser.getPassword(),
                appUser.isEnabled(),
                true, true, true,
                simpleGrantedAuthorityList
        );

        return userDetails;
    }
}
