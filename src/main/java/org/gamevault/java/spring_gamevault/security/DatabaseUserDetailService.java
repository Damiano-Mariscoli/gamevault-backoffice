package org.gamevault.java.spring_gamevault.security;

import java.util.Optional;

import org.gamevault.java.spring_gamevault.model.User;
import org.gamevault.java.spring_gamevault.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userAttempt = userRepository.findByUsername(username);

        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("Non ci sono utenti disponibili con l'username: " + username);
        }

        return new DatabaseUserDetails(userAttempt.get());
    }
}
