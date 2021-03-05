import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {Recipe} from '../recipe';
import { RecipeService} from "../recipe.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {
 @Input() recipe: Recipe = {}
  versions: Recipe[] = [];
  newRecipe: Recipe ={}


  constructor(private route: ActivatedRoute,
              private recipeService: RecipeService,
              private location: Location) {
  }

  ngOnInit(): void {
   this.route.params.pipe(
      map(params => params.id),
    ).subscribe(id => {
       this.getRecipe(id)
       this.getVersions(id)
     }
   );

  }

  getRecipe(id: number): void {
    this.recipeService.getRecipe(id)
      .subscribe(recipe => this.recipe = recipe)
  }

  getVersions(id: number): void{
    this.recipeService.getVersions(id)
      .subscribe(versions => this.versions = versions);
  }

  update():void{
    let id = this.recipe?.id
    if(id!=null){
      this.recipeService.updateReceipt(id, this.recipe).subscribe(
        v => {
          this.getRecipe(id!)
          this.getVersions(id!)
        }
      )
    }
  }

  addChild(){
    this.newRecipe.parentId = this.recipe?.id
    this.recipeService.postReceipt(this.newRecipe).subscribe(
      value => {}
    )
  }

  goBack(): void {
    this.location.back();
  }

}
