package com.dev.cookbookapplication.service;

import com.dev.cookbookapplication.entity.Recipe;
import com.dev.cookbookapplication.repository.RecipeRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.getAll();
    }

    @Override
    public void add(Recipe recipe) {
        recipe.setDateCreated(LocalDate.now());
        recipeRepository.save(recipe);
    }

    @Override
    public void update(Recipe recipe, Long id) {
        recipe.setId(id);
        recipe.setDateCreated(LocalDate.now());
        Recipe oldRecipe = new Recipe();
        Recipe temp = findById(id);
        oldRecipe.setName(temp.getName());
        oldRecipe.setDateCreated(temp.getDateCreated());
        oldRecipe.setDescription(temp.getDescription());
        oldRecipe.setParentId(temp.getParentId());
        oldRecipe.setOriginalVersionId(temp.getId());
        recipeRepository.save(oldRecipe);
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).get();
    }

    @Override
    public List<Recipe> getAllPreviousVersions(Long id) {
        return recipeRepository.getAllPreviousVersions(id);
    }

}
