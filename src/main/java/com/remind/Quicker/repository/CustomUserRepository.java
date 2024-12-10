package com.remind.Quicker.repository;

import com.remind.Quicker.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {

    CustomUser findByEmail(String username);
}
