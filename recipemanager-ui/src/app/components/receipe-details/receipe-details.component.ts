import { Receipe } from './../../models/receipe';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-receipe-details',
  templateUrl: './receipe-details.component.html',
  styleUrls: ['./receipe-details.component.css'],
})
export class ReceipeDetailsComponent implements OnInit {
  recipe: Receipe;
  errorMsg: any;
  constructor(
    private recipeService: RecipeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const recipeFromRoute = routeParams.get('id');

    this.recipeService
      .getRecipe(recipeFromRoute)
      .subscribe((res) => (this.recipe = res), err=>{
        console.log("error while getting r by id",recipeFromRoute,err);
        
        this.errorMsg = err;
      });
  }
}
