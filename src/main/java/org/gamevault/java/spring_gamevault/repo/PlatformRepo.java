package org.gamevault.java.spring_gamevault.repo;

import org.gamevault.java.spring_gamevault.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepo extends JpaRepository <Platform , Integer> {
    
}
