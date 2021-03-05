import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../recipe";
import {RecipeService} from '../recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {
  recipes: Recipe[] = [];
  newRecipe: Recipe| undefined ={}

  constructor(private recipeService: RecipeService) {
  }

  ngOnInit(): void {
    this.getRecipes();
  }

  addRecipe():void{
    this.recipeService.postReceipt(this.newRecipe).subscribe(
       v=> this.getRecipes()
    )
  }

  getRecipes(): void {
    this.recipeService.getRecipes().subscribe(recipes => this.recipes = recipes);
  }

}
