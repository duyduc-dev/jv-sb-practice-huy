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


    public void insertNodeToTree(TreeCategoryResponse root, Category category) {
        // di toi level
        // kiếm node có parentId
        // add
        int parentLevel = category.getLevel() - 1;
        if(root.getLevel() == parentLevel && Objects.equals(root.getId(), category.getParentId())) {
            root.getChildren().add(new TreeCategoryResponse(category));
            return;
        }
        for (TreeCategoryResponse treeCategoryResponse: root.getChildren()) {
            if(treeCategoryResponse.getLevel() != parentLevel) {
                insertNodeToTree(treeCategoryResponse, category);
            } else if(treeCategoryResponse.getId().equals(category.getParentId())) {
                treeCategoryResponse.getChildren().add(this.convertCategoryToTreeItem(category));
            }
        }
    }


}
