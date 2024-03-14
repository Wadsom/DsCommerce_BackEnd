package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.Config.AppConfig;
import com.wpCorp.dsCommerce.DTO.RoleDTO;
import com.wpCorp.dsCommerce.DTO.UserDTO;
import com.wpCorp.dsCommerce.Entity.RoleEntity;
import com.wpCorp.dsCommerce.Entity.UserEntity;
import com.wpCorp.dsCommerce.Repository.RoleRepository;
import com.wpCorp.dsCommerce.Repository.UserRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.UserExistsException;
import com.wpCorp.dsCommerce.Service.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private AppConfig auth;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        List<UserEntity> result = userRepo.findAll();
        return result.stream().map(UserDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Long id) {
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        try {
            UserEntity user = new UserEntity();
            user.setName(dto.getName());
            user.setBirth_date(dto.getBithdate());
            user.setPhone(dto.getPhone());
            user.setPassword(auth.passwordEncoder().encode(dto.getPassword()));
            user.setEmail(dto.getEmail());
            for (RoleDTO roleDTO : dto.getRoles()) {
                RoleEntity role = roleRepo.getReferenceById(roleDTO.getId());
                user.addRoles(role);
            }
            user = userRepo.save(user);
            return new UserDTO(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistsException("User already exists");
        }
    }


}
