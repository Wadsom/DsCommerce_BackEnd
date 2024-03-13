package com.wpCorp.dsCommerce.Resource;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.DTO.ProductMinDTO;
import com.wpCorp.dsCommerce.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @Autowired
    private CategoryService categoryServ;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> result = categoryServ.getAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable Long id) {
        CategoryDTO dto = categoryServ.getById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insertCategory(@RequestBody @Valid CategoryDTO dto) {
        dto = categoryServ.createCateg(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
