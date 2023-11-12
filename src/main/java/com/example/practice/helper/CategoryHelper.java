package com.example.practice.helper;

import com.example.practice.controllers.models.response.TreeCategoryResponse;
import com.example.practice.entities.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CategoryHelper {
    public TreeCategoryResponse convertCategoryToTreeItem(Category category) {
       return TreeCategoryResponse.builder()
               .id(category.getId())
               .level(category.getLevel())
               .name(category.getName())
               .children(new ArrayList<>())
               .build();
    }


    public void addChildToParent(TreeCategoryResponse root, int level, Category category) {
        // di toi level
        // kiếm node có parentId
        // add
        if(root.getLevel() == level && Objects.equals(root.getId(), category.getParentId())) {
            root.getChildren().add(this.convertCategoryToTreeItem(category));
            return;
        }
        for (TreeCategoryResponse treeCategoryResponse: root.getChildren()) {
            if(treeCategoryResponse.getLevel() != level) {
                addChildToParent(treeCategoryResponse, level, category);
            } else if(treeCategoryResponse.getId().equals(category.getParentId())) {
                treeCategoryResponse.getChildren().add(this.convertCategoryToTreeItem(category));
            }
        }
    }


}
