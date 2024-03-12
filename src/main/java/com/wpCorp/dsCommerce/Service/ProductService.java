package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.DTO.ProductMinDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.DatabaseException;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductExistsException;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> getAll(Pageable pageable, String name) {
        Page<ProductEntity> listEntity = productRepo.searchByName(name, pageable);
        return listEntity.map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductMinDTO findById(Long id) {
        ProductEntity prod = productRepo.findById(id).orElseThrow(() -> new ProductNotFound("Product not found!"));
        return new ProductMinDTO(prod);
    }

    @Transactional
    public ProductMinDTO newProd(ProductMinDTO dto) {
        ProductEntity item = new ProductEntity();
        item.setName(dto.getName());
        item.setImgUrl(dto.getImgUrl());
        item.setLongDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        for (CategoryDTO cat : dto.getCategories()) {
            CategoryEntity cate = categoryRepo.getReferenceById(cat.getId());
            item.addCategories(cate);
        }
        item = productRepo.save(item);
        return new ProductMinDTO(item);
    }

    @Transactional
    public ProductMinDTO alterMode(Long id, ProductMinDTO dto) {
        ProductEntity prod = productRepo.findById(id).orElseThrow(() -> new ProductNotFound("Product not found!"));
        prod.setName(dto.getName());
        prod.setPrice(dto.getPrice());
        prod.setLongDescription(dto.getDescription());
        prod.setImgUrl(dto.getImgUrl());
        for (CategoryDTO cat : dto.getCategories()) {
            CategoryEntity cate = categoryRepo.getReferenceById(cat.getId());
            prod.addCategories(cate);
        }
        prod = productRepo.save(prod);
        return new ProductMinDTO(prod);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepo.existsById(id)) throw new ProductNotFound("Product not found");
        try {
            productRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integration Violation");
        }

    }


    @Transactional(readOnly = true)
    private Boolean checkProd(ProductMinDTO dto) {
        List<ProductEntity> result = productRepo.findAll();
        for (ProductEntity ent : result) {
            if (ent.equals(dto)) {
                return true;
            }
        }
        return false;
    }
}



