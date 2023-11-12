package com.example.practice.service;

import com.example.practice.common.enums.SystemStatus;
import com.example.practice.common.exception.ApplicationException;
import com.example.practice.common.response.RestAPIStatus;
import com.example.practice.common.response.RestApiMessage;
import com.example.practice.common.validator.Validator;
import com.example.practice.controllers.models.request.CategoryRequest;
import com.example.practice.controllers.models.request.UpdateCategoryRequest;
import com.example.practice.controllers.models.response.TreeCategoryResponse;
import com.example.practice.entities.Category;
import com.example.practice.helper.CategoryHelper;
import com.example.practice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryHelper categoryHelper;

    @Override
    public List<Category> getListCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<TreeCategoryResponse> getTreeCategory() {
        List<Category> categoryList = this.categoryRepository.findAllOrderLevel();
        TreeCategoryResponse treeCategoryResponses = TreeCategoryResponse.builder()
                .id("root")
                .name("root")
                .level(-1)
                .children(new ArrayList<>())
                .build();

        for (Category category: categoryList) {
            if(category.getParentId() == null) {
                treeCategoryResponses.getChildren().add(categoryHelper.convertCategoryToTreeItem(category));
            } else {
                categoryHelper.addChildToParent(treeCategoryResponses, category.getLevel() - 1, category);
            }

        }
        return treeCategoryResponses.getChildren();
    }

    @Override
    public TreeCategoryResponse getTreeCategoryById(String id) {
        Category category = this.getCategoryById(id);
        TreeCategoryResponse root = this.categoryHelper.convertCategoryToTreeItem(category);
        List<Category> chidren = this.categoryRepository.findAllByLevelGreaterThanOrderByLevel(category.getLevel());
        for (Category  childItem: chidren) {
            categoryHelper.addChildToParent(root, childItem.getLevel() - 1, childItem);
        }
        return root;
    }

    @Override
    public List<Category> getCategoriesByParentId(String parentId) {
        return this.categoryRepository.findByParentId(parentId);
    }

    @Override
    public Category getCategoryById(String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        Validator.notNull(category, RestAPIStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        return category;
    }

    @Override
    public void insertCategory(CategoryRequest categoryRequest) {
        Validator.notNullAndNotEmptyString(categoryRequest.getName(), RestAPIStatus.BAD_REQUEST, RestApiMessage.INVALID_REQUEST);
        Category categoryName = this.categoryRepository.findByName(categoryRequest.getName());
        Validator.mustNull(categoryName, RestAPIStatus.EXISTED, RestApiMessage.NAME_ALREADY_EXIST);
        Category categoryByChildrenParentId = null;
        if(categoryRequest.getParentId() != null) {
            Validator.notEmpty(categoryRequest.getParentId(), RestAPIStatus.BAD_PARAMS,RestApiMessage.PARENT_ID_CANNOT_EMPTY);
            categoryByChildrenParentId = this.getCategoryById(categoryRequest.getParentId());
        }
        boolean isParentIdNull = categoryByChildrenParentId == null;
        String categoryParentId = isParentIdNull ? null : categoryByChildrenParentId.getId();
        int level = isParentIdNull ? 0 : categoryByChildrenParentId.getLevel() + 1;
        Category category = Category.builder()
            .parentId(categoryParentId)
            .name(categoryRequest.getName())
            .level(level)
            .status(SystemStatus.ACTIVE)
            .build();
        this.categoryRepository.save(category);
    }

    @Override
    public void updateCategory(String id, UpdateCategoryRequest categoryRequest) {
        Validator.notNullAndNotEmptyString(categoryRequest.getName(), RestAPIStatus.BAD_REQUEST, RestApiMessage.INVALID_REQUEST);
        Category updateCategory = this.categoryRepository.findById(id).orElse(null);
        Validator.notNull(updateCategory, RestAPIStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        Category categoryName = this.categoryRepository.findByName(categoryRequest.getName());
        Validator.mustTrue(
                !updateCategory.getId().equals(categoryName.getId()) && categoryName.getName().equals(categoryRequest.getName()),
                RestAPIStatus.EXISTED,
                RestApiMessage.NAME_ALREADY_EXIST
        );
        Validator.mustTrue(
                updateCategory.getId().equals(categoryName.getId())  && categoryName.getName().equals(categoryRequest.getName()),
                RestAPIStatus.EXISTED,
                RestApiMessage.DATA_NOT_CHANGE
        );
        updateCategory.setName(categoryRequest.getName());
        this.categoryRepository.save(updateCategory);
    }

    @Override
    public void deleteCategory(String id, boolean allowDeleteChildren) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        Validator.notNull(category, RestAPIStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        System.out.println(category);
        if(allowDeleteChildren) {
            List<Category> categoryList = this.categoryRepository.findByParentId(category.getId());
            List<Category> categoriesDelete = new ArrayList<>();
            getCategoriesWithChild(categoryList, categoriesDelete);
            this.categoryRepository.deleteAll(categoriesDelete);
        } else {
            List<Category> categoryList = this.getCategoriesByParentId(category.getId());
            Validator.mustEqual(categoryList.size(), 0, RestAPIStatus.CONFLICT, RestApiMessage.CANNOT_DELETED_WHILE_CHILDREN_EXISTED);
        }
        this.categoryRepository.deleteById(category.getId());
    }


    private void getCategoriesWithChild(List<Category> categoryList, List<Category> categoriesDelete) {
        categoriesDelete.addAll(categoryList);
        for (Category category: categoryList) {
            String parentId = category.getId();
            List<Category> list = this.getCategoriesByParentId(parentId);
            getCategoriesWithChild(list, categoriesDelete);
        }
    }


}
