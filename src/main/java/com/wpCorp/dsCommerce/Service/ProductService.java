package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.DTO.ProductMinDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Repository.ProductRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductExistsException;
import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        if (productRepo.findByName(dto.getName())) throw new ProductExistsException("Este produto já existe!");
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
}
