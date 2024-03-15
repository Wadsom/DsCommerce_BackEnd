package com.wpCorp.dsCommerce.Repository;

import com.wpCorp.dsCommerce.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT tb_role.id , tb_role.authority" +
            " FROM tb_role " +
            "WHERE UPPER(tb_role.authority) LIKE UPPER( CONCAT( '%' , ':roleName', '%' ) );")
    RoleEntity searchRole(String roleName);
}
