package com.remind.Quicker.repository;

import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {

    @Query("SELECT c FROM Product c WHERE c.deleteStatus='ACTIVE'")
    Page<CustomUser> findAllActivatedProduct(PageRequest pageable);

    @Query("SELECT c FROM OrderStatus c WHERE c.id=(SELECT MAX(cu.id) FROM OrderStatus cu)")
    OrderStatus findRecentEntry();
}
