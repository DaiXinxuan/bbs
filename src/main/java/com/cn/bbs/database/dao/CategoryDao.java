package com.cn.bbs.database.dao;

import com.cn.bbs.database.model.CategoryEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface CategoryDao {
    int addNewCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> getAllCategories();

    int updateCategoryInfo(CategoryEntity categoryEntity);

    int deleteCategory(int categoryId);

    CategoryEntity getCategoryById(int categoryId);
}
