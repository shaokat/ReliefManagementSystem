package org.fteller.user.repositories;

import org.fteller.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    @Query(value = "SELECT user.username FROM AppUser user")
    List<String> getAllUsername();
}