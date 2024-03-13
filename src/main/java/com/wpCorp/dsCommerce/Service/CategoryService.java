package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> result = categoryRepo.findAll();
        List<CategoryDTO> dtos = result.stream().map(CategoryDTO::new).toList();
        return dtos;
    }

    @Transactional(readOnly = true)
    public CategoryDTO getById(Long id) {
        CategoryEntity ent = categoryRepo.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found"));
        return new CategoryDTO(ent);
    }

    @Transactional
    public CategoryDTO createCateg(CategoryDTO dto) {
        CategoryEntity cate = new CategoryEntity();
        cate.setName(dto.getName());
        cate = categoryRepo.save(cate);
        return new CategoryDTO(cate);
    }
}
