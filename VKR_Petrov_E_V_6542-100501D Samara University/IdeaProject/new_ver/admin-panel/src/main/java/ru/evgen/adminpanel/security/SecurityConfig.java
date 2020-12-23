package ru.evgen.adminpanel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.evgen.adminpanel.entity.Role;
import ru.evgen.adminpanel.entity.User;
import ru.evgen.adminpanel.repository.RoleRepository;
import ru.evgen.adminpanel.repository.UserRepository;
import ru.evgen.adminpanel.service.UserDetailsServiceImpl;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/static/css/**","/static/**").permitAll()
                .antMatchers("/roles","/users").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)        // set invalidation state when logout
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60) // 24h
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        Role roleAdmin = new Role(UUID.fromString("00000000-0000-0000-0000-000000000001"),"ROLE_ADMIN");
        roleAdmin.setUsers(new HashSet<>());

        Role roleUser = new Role(UUID.fromString("22222222-0000-0000-0000-000000000002"),"ROLE_USER");
        roleUser.setUsers(new HashSet<>());

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User admin = new User("admin","admin",passwordEncoder().encode("admin"));
        userRepository.save(admin);
        roleAdmin.getUsers().add(admin);
        roleUser.getUsers().add(admin);
        admin.getRoles().add(roleAdmin);
        admin.getRoles().add(roleUser);
        userRepository.save(admin);

        User user1 = new User("evgen","evgen",passwordEncoder().encode("evgen"));
        userRepository.save(user1);
        user1.getRoles().add(roleUser);
        roleUser.getUsers().add(user1);
        userRepository.save(user1);


        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());



//        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("evpetrov").password(encoder.encode("123")).roles("USER");
//        auth.inMemoryAuthentication().withUser("ivsidorov").password(encoder.encode("123")).roles("USER");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}