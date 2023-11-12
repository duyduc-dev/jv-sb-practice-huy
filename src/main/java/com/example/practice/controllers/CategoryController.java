package com.example.practice.controllers;

import com.example.practice.common.enums.SystemStatus;
import com.example.practice.controllers.models.request.CategoryRequest;
import com.example.practice.entities.Category;
import com.example.practice.repositories.CategoryRepository;
import com.example.practice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractBaseController{

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest) {
        this.categoryService.insertCategory(categoryRequest);
        return responseUtil.successResponse("ok");
    }

    @PostMapping("/all")
    public ResponseEntity createAllCategory(@RequestBody List<Category> C   ) {

        categoryRepository.saveAll(C);
        return responseUtil.successResponse("ok");
    }

    @GetMapping
    public ResponseEntity getCategories() {
        return responseUtil.successResponse(this.categoryService.getListCategory());
    }

    @GetMapping("/tree")
    public ResponseEntity getTreeCategories() {
        return responseUtil.successResponse(this.categoryService.getTreeCategory());
    }

    @GetMapping("/tree/{id}")
    public ResponseEntity getTreeCategories(@PathVariable("id") String id) {
        return responseUtil.successResponse(this.categoryService.getTreeCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(
            @PathVariable("id") String id,
            @RequestParam(value = "allow_delete_children", required = false) boolean allowDeleteChildren
    ) {
        this.categoryService.deleteCategory(id, allowDeleteChildren);
        return responseUtil.successResponse("ok");
    }

}
