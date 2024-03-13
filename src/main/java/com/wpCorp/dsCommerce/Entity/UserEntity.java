package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Email(message = "Insira um email valido")
    @NotEmpty
    private String email;
    @Size(min = 11, max = 11, message = "Insira um número com Estado de celular valido")
    @NotEmpty
    private String phone;
    @NotEmpty
    private LocalDate bithdate;
    @Size(min = 6, max = 16, message = "Insira uma senha valida")
    @NotEmpty
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String name, String email, String phone, LocalDate bithdate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bithdate = bithdate;
        this.password = password;
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void addRoles(RoleEntity role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName) {
        for (RoleEntity role : getRoles()) {
            if (role.getAuthority().equals(roleName)) return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
