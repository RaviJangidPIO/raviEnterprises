package com.remind.Quicker.repository;

import com.remind.Quicker.entities.CustomUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {

    CustomUser findByEmail(String username);

    @Query("UPDATE CustomUser c SET c.deleteStatus = 'DE_ACTIVATED' WHERE c.id=:id")
    void deactivateUser(@Param("id") Long id);

    @Query("SELECT c FROM CustomUser c WHERE c.deleteStatus='ACTIVE'")
    Page<CustomUser> findAllActivatedUsers(PageRequest pageable);
}
