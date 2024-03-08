package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    @Query(nativeQuery = true,value = "SELECT * FROM tb_product AS prd " +
//            "INNER JOIN tb_products_category AS prct ON prct.product_id = prd.id" +
//            " INNER JOIN tb_category AS ct ON ct.id = prct.category_id = ct.id" +
//            " WHERE prd.name = :name ")



}
