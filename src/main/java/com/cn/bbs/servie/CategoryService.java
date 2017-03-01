package com.cn.bbs.servie;

import com.cn.bbs.database.model.CategoryEntity;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
public interface CategoryService {
    int addNewCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> getAllCategories();

    int updateCategoryInfo(CategoryEntity categoryEntity);

    int deleteCategory(int categoryId);

    CategoryEntity getCategoryById(int categoryId);
}
