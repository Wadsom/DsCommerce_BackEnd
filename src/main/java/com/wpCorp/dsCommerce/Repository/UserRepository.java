package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = " SELECT obj FROM UserEntity obj WHERE UPPER(obj.name) LIKE UPPER( CONCAT( '%', ':name' , '%' )) ")
    Page<UserEntity> searchByName(Pageable page, String name);
}
