package com.wpCorp.dsCommerce.Resource;

import com.wpCorp.dsCommerce.DTO.UserDTO;
import com.wpCorp.dsCommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    @Autowired
    private UserService userServ;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> list = userServ.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO user = userServ.getUser(id);
        return ResponseEntity.ok().body(user);
    }

}
