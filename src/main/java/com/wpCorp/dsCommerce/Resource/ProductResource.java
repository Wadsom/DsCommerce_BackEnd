package com.wpCorp.dsCommerce.Resource;

import com.wpCorp.dsCommerce.DTO.ProductMinDTO;
import com.wpCorp.dsCommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService productServ;

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> getAll(@RequestParam(name = "name", defaultValue = "") String name,
                                                      Pageable pageable) {
        Page<ProductMinDTO> pageDTO = productServ.findAll(name, pageable);
        return ResponseEntity.ok(pageDTO);
    }

}
