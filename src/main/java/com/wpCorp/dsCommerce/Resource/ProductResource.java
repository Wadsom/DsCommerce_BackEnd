package com.wpCorp.dsCommerce.Resource;

import com.wpCorp.dsCommerce.DTO.ProductMinDTO;
import com.wpCorp.dsCommerce.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {
    @Autowired
    private ProductService productServ;

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> getAll(Pageable pageable,
                                                      @RequestParam(name = "name", defaultValue = "")
                                                      String name) {
        Page<ProductMinDTO> pageDTO = productServ.getAll(pageable, name);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductMinDTO> findById(@PathVariable Long id) {
        ProductMinDTO prodDTO = productServ.findById(id);
        return ResponseEntity.ok(prodDTO);
    }

    @PostMapping
    public ResponseEntity<ProductMinDTO> insert(@RequestBody @Valid ProductMinDTO dto) {
        dto = productServ.newProd(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductMinDTO> updateProd(@PathVariable Long id, @RequestBody @Valid ProductMinDTO dto) {
        dto = productServ.alterMode(id, dto);
        return ResponseEntity.ok().body(dto);
    }



}
