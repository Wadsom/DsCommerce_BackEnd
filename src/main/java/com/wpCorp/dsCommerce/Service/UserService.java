package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.UserDTO;
import com.wpCorp.dsCommerce.Entity.UserEntity;
import com.wpCorp.dsCommerce.Repository.UserRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

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


}
