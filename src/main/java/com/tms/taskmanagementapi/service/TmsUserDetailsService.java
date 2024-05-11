package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.entity.Registration;
import com.tms.taskmanagementapi.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TmsUserDetailsService implements UserDetailsService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         Optional<Registration> user = registrationRepository.findByEmail(username);
        return user.map(TmsUserDetails::new)
                .orElseThrow(() -> new BadCredentialsException("Invalid Username or Password"));
    }
}
