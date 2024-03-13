package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT ct.id,ct.name,ct.created_at" +
            " FROM tb_category AS ct WHERE UPPER(ct.name) LIKE UPPER(CONCAT('%',':name','%')); ")
    List<CategoryEntity> searchByName(String name);


}
