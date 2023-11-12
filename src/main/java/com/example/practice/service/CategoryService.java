package com.example.practice.service;

import com.example.practice.controllers.models.request.CategoryRequest;
import com.example.practice.controllers.models.request.UpdateCategoryRequest;
import com.example.practice.controllers.models.response.TreeCategoryResponse;
import com.example.practice.entities.Category;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CategoryService {

    List<Category> getListCategory();
    List<TreeCategoryResponse> getTreeCategory();
    TreeCategoryResponse getTreeCategoryById(String id);
    List<Category> getCategoriesByParentId(String parentId);

    Category getCategoryById(String id);

    void insertCategory(CategoryRequest categoryRequest);
    void updateCategory(String id, UpdateCategoryRequest updateCategoryRequest);
    void deleteCategory(String id, boolean allowDeleteChildren);
}
