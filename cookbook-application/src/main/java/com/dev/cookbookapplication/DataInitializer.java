package com.dev.cookbookapplication;

import com.dev.cookbookapplication.entity.Recipe;
import com.dev.cookbookapplication.service.RecipeService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final RecipeService recipeService;

    @PostConstruct
    private void insertData() {
        Recipe chickenStrips = new Recipe();
        Recipe honeyPie = new Recipe();
        chickenStrips.setName("Chicken strips");
        chickenStrips.setDescription("Fuck your chicken strips!");
        chickenStrips.setDateCreated(LocalDate.now());
        recipeService.add(chickenStrips);
        honeyPie.setName("Honey Pie");
        honeyPie.setDescription("ehehe");
        honeyPie.setDateCreated(LocalDate.now());
        honeyPie.setParentId(1L);
        recipeService.add(honeyPie);

        for (int i = 0; i < 3; i++) {
            honeyPie.setName("HP v "+ i+1);
            honeyPie.setDescription("Honey Pie v "+ i+1);
            recipeService.update(honeyPie, 2L);
        }

    }
}
