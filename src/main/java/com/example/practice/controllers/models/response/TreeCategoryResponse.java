package com.example.practice.controllers.models.response;

import com.example.practice.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TreeCategoryResponse {
    private String id;
    private String name;
    private int level;
    private List<TreeCategoryResponse> children;

    public TreeCategoryResponse() {
        this.id = "__root__";
        this.name = "__root__";
        this.level = -1;
        this.children = new ArrayList<>();
    }

    public TreeCategoryResponse(Category category) {
        this.id = category.getId();
        this.level = category.getLevel();
        this.name = category.getName();
        this.children = new ArrayList<>();
    }
}
