package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Transactional(readOnly = true)
    public ProductDTO findAll(String name, Pageable pageable){

    }


}