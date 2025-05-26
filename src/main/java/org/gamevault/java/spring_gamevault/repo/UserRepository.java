package org.gamevault.java.spring_gamevault.repo;

import java.util.Optional;

import org.gamevault.java.spring_gamevault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
}

