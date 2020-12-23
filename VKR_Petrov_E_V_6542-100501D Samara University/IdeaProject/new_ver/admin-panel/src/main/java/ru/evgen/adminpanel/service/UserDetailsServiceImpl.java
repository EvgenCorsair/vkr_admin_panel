package ru.evgen.adminpanel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.evgen.adminpanel.entity.Role;
import ru.evgen.adminpanel.repository.RoleRepository;
import ru.evgen.adminpanel.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//import com.evgeniy.test1.SpringBootAppEvgenTest.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UUID uuidName = UUID.fromString(username);
        ru.evgen.adminpanel.entity.User user = userRepository.getUserByEmail(username);

        if (user == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found User: " + user.getName());
        Set<Role> roles = userRepository.getUserByEmail(username).getRoles();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        }
        roleRepository.findAll().forEach(e-> {
            System.out.println(e.getName()+"            "+e.getRoleId());
            e.getUsers().forEach(t-> System.out.println(t));
        });
        System.out.println("aaa");

        UserDetails userDetails = (UserDetails) new User(user.getEmail(),
                user.getPassword(), grantList);

        return userDetails;
    }

}
