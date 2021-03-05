package com.dev.cookbookapplication.service;

import com.dev.cookbookapplication.dto.RecipeListDto;
import com.dev.cookbookapplication.dto.RecipeRequestDto;
import com.dev.cookbookapplication.dto.RecipeResponseDto;
import com.dev.cookbookapplication.entity.Recipe;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public RecipeResponseDto createDtoFromEntity(Recipe recipe) {
        RecipeResponseDto dto = new RecipeResponseDto();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDateCreated(recipe.getDateCreated());
        dto.setDescription(recipe.getDescription());
        dto.setParentId(recipe.getParentId());
        return dto;
    }

    public Recipe createEntityFromDto(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setDateCreated(LocalDate.now());
        recipe.setParentId(dto.getParentId());
        return recipe;
    }

    public RecipeListDto createListResponse(List<Recipe> children, Recipe parent){
        List<RecipeResponseDto> dtos = children.stream()
            .map(this::createDtoFromEntity)
            .collect(Collectors.toList());
        RecipeListDto response = new RecipeListDto();
        response.setChildren(dtos);
        response.setId(parent.getId());
        response.setDateCreated(parent.getDateCreated());
        response.setName(parent.getName());
        response.setParentId(parent.getParentId());
        response.setDescription(parent.getDescription());
        return response;
    }
}
