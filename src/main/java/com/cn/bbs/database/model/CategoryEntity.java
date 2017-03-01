package com.cn.bbs.database.model;

import com.cn.bbs.param.CategoryParam;

/**
 * Created by dxx on 2017/2/22.
 */
public class CategoryEntity {
    private int categoryId;
    private String name;
    private String description;

    public CategoryEntity() {}

    public CategoryEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryEntity(CategoryParam categoryParam) {
        this.name = categoryParam.getName();
        this.description = categoryParam.getDescription();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
