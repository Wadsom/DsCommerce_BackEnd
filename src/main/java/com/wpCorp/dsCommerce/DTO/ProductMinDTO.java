package com.wpCorp.dsCommerce.DTO;

import com.wpCorp.dsCommerce.Entity.CategoryEntity;
import com.wpCorp.dsCommerce.Entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMinDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductMinDTO() {
    }

    public ProductMinDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductMinDTO(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getLongDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        for (CategoryEntity cate : entity.getCategories()) {
            categories.add(new CategoryDTO(cate.getId(), cate.getName()));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
