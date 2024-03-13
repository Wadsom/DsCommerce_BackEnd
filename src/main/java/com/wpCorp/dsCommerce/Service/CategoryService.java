package com.wpCorp.dsCommerce.Service;

import com.wpCorp.dsCommerce.DTO.CategoryDTO;
import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Repository.CategoryRepository;
import com.wpCorp.dsCommerce.Service.Exceptions.CategoryExistsException;
import com.wpCorp.dsCommerce.Service.Exceptions.CategoryNotFoundException;
import com.wpCorp.dsCommerce.Service.Exceptions.DatabaseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        if (checkList(dto.getName())) throw new CategoryExistsException("Category already exists");
        CategoryEntity cate = new CategoryEntity();
        cate.setName(dto.getName());
        cate = categoryRepo.save(cate);
        return new CategoryDTO(cate);
    }

    @Transactional
    public CategoryDTO alterCategory(Long id, CategoryDTO dto) {
        CategoryEntity ent = categoryRepo.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category not found"));
        BeanUtils.copyProperties(dto, ent);
        ent = categoryRepo.save(ent);
        return new CategoryDTO(ent);
    }

    @Transactional(readOnly = true)
    private Boolean checkList(String name) {
        for (CategoryEntity ent : categoryRepo.findAll()) {
            if (ent.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (categoryRepo.existsById(id)) throw new CategoryNotFoundException("Category not found");
        try {
            categoryRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integration Violaiton");
        }
    }
}
