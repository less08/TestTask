package com.dev.cookbookapplication.service;

import com.dev.cookbookapplication.entity.Recipe;
import java.util.List;

public interface RecipeService {
    List<Recipe> getAll();

    void add(Recipe recipe);

    void update(Recipe recipe, Long id);

    Recipe findById(Long id);

    List<Recipe> getAllPreviousVersions(Long id);
}
