package com.dev.cookbookapplication.dto;

import lombok.Data;

@Data
public class RecipeRequestDto {
    private String name;
    private String description;
    private Long parentId;
}
