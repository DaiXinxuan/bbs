package com.cn.bbs.servie.impl;

import com.cn.bbs.database.dao.CategoryDao;
import com.cn.bbs.database.model.CategoryEntity;
import com.cn.bbs.servie.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/2/22.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public int addNewCategory(CategoryEntity categoryEntity) {
        return categoryDao.addNewCategory(categoryEntity);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public int updateCategoryInfo(CategoryEntity categoryEntity) {
        return categoryDao.updateCategoryInfo(categoryEntity);
    }

    public int deleteCategory(int categoryId) {
        return categoryDao.deleteCategory(categoryId);
    }

    public CategoryEntity getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }
}
