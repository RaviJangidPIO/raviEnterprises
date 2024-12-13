package com.remind.Quicker.repository;

import com.remind.Quicker.entities.ItemOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderedRepository extends JpaRepository<ItemOrdered,Long> {

}
