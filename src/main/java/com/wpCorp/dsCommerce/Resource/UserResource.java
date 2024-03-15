package com.wpCorp.dsCommerce.Resource;

import com.wpCorp.dsCommerce.DTO.UserDTO;
import com.wpCorp.dsCommerce.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.directory.SearchResult;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO dto) {
        dto = userServ.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> alterUser(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        dto = userServ.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
