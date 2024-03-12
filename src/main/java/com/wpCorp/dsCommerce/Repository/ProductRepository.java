package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT obj FROM ProductEntity obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name , '%' ))")
    Page<ProductEntity> searchByName(String name, Pageable pageable);


}
