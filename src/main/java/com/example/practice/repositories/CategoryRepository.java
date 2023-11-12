package com.example.practice.repositories;

import com.example.practice.common.enums.SystemStatus;
import com.example.practice.controllers.models.response.TreeCategoryResponse;
import com.example.practice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);

    List<Category> findByParentId(String parentId);

    @Query("""
    select c from Category c order by c.level
    """)
    List<Category> findAllOrderLevel();

    List<Category> findAllByLevelGreaterThanOrderByLevel(int level);
}
