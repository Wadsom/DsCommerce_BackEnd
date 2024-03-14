package com.wpCorp.dsCommerce.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 8, max = 17, message = "Insira um número com Estado de celular valido")
    private String name;
    @Email(message = "Insira um email valido")
    @Column(unique = true)
    private String email;
    @Size(min = 11, max = 11, message = "Insira um número com Estado de celular valido")
    private String phone;
    private LocalDate birth_date;
    @Size(message = "Insira uma senha valida")
    private String password;
    @ManyToMany
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String name, String email, String phone, LocalDate bithdate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = bithdate;
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

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
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
