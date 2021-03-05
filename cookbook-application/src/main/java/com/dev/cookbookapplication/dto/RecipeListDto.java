package com.dev.cookbookapplication.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
//make this previous versions dto
public class RecipeListDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateCreated;
    private Long parentId;
    private List<RecipeResponseDto> children;
}
