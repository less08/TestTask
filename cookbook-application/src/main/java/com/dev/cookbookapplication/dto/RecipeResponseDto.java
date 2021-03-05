package com.dev.cookbookapplication.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateCreated;
    private Long parentId;
}
