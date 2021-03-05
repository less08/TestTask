package com.dev.cookbookapplication.controller;

import com.dev.cookbookapplication.dto.RecipeListDto;
import com.dev.cookbookapplication.dto.RecipeRequestDto;
import com.dev.cookbookapplication.dto.RecipeResponseDto;
import com.dev.cookbookapplication.entity.Recipe;
import com.dev.cookbookapplication.service.Mapper;
import com.dev.cookbookapplication.service.RecipeService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final Mapper mapper;

    @GetMapping
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeService.getAll().stream()
            .map(mapper::createDtoFromEntity)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecipeResponseDto getRecipeDetails(@PathVariable Long id) {
        return mapper.createDtoFromEntity(recipeService.findById(id));
    }

    @GetMapping("/{id}/history")
    public List<RecipeResponseDto> getHistory(@PathVariable Long id){
        return recipeService.getAllPreviousVersions(id).stream()
            .map(mapper::createDtoFromEntity)
            .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addRecipe(@RequestBody RecipeRequestDto dto) {
        recipeService.add(mapper.createEntityFromDto(dto));
    }

    @PutMapping("/{id}")
    public void updateRecipe(@RequestBody RecipeRequestDto dto, @PathVariable Long id) {
        Recipe recipe = mapper.createEntityFromDto(dto);
        recipeService.update(recipe,id);
    }

}
