package com.wpCorp.dsCommerce.DTO;

import com.wpCorp.dsCommerce.Entity.RoleEntity;
import com.wpCorp.dsCommerce.Entity.UserEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate bithdate;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(String name, String email, String phone, LocalDate bithdate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bithdate = bithdate;
        this.password = password;
    }

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.bithdate = user.getBirth_date();
        for (RoleEntity role : user.getRoles()) {
            addRoles(role);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBithdate() {
        return bithdate;
    }

    public void setBithdate(LocalDate bithdate) {
        this.bithdate = bithdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void addRoles(RoleEntity role) {
        roles.add(new RoleDTO(role));
    }


}

