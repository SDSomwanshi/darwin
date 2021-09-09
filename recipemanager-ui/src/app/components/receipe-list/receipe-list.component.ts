import { Receipe } from './../../models/receipe';
import { RecipeService } from './../../services/recipe-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-receipe-list',
  templateUrl: './receipe-list.component.html',
  styleUrls: ['./receipe-list.component.css'],
})
export class ReceipeListComponent implements OnInit {
  recipes: Receipe[] = [];
  errorMsg;
  constructor(private recipeService: RecipeService) {}

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe((res:any) => (this.recipes = res.items)
    , err=>{
      console.log("error while getting recipe",err);
      
      this.errorMsg = err;
    });
  }
}
