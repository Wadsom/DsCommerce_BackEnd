package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_category AS ct WHERE UPPER(ct.name) LIKE UPPER(CONCAT('%',':name','%')); ")
    Page<CategoryEntity> searchByName(String name, Pageable pageable);


}
