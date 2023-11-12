package com.example.practice.controllers.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeCategoryResponse {
    private String id;
    private String name;
    private int level;
    private List<TreeCategoryResponse> children;
}
