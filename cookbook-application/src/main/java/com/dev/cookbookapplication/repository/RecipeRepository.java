package com.dev.cookbookapplication.repository;

import com.dev.cookbookapplication.entity.Recipe;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select r from Recipe r where r.originalVersionId is null order by r.name")
    List<Recipe> getAll();

    Optional<Recipe> findById(Long id);

    @Query("select r from Recipe r where r.originalVersionId=?1 order by r.dateCreated")
    List<Recipe> getAllPreviousVersions(Long id);
}
