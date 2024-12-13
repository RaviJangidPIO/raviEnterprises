package com.remind.Quicker.repository;

import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("UPDATE Product c SET c.deleteStatus = 'DE_ACTIVATED' WHERE c.id=:id")
    void deactivateProduct(Long id);

    @Query(value = "SELECT * FROM product WHERE delete_status='ACTIVE'",nativeQuery = true)
    Page<Product> findAllActivatedProduct(PageRequest pageable);

}
