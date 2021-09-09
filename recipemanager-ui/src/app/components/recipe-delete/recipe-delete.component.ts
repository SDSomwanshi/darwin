import { Receipe } from './../../models/receipe';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-recipe-delete',
  templateUrl: './recipe-delete.component.html',
  styleUrls: ['./recipe-delete.component.css']
})
export class RecipeDeleteComponent implements OnInit {
    recipe: Receipe;
      errorMsg: any;
  constructor(private recipeService: RecipeService,
                  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
        const routeParams = this.route.snapshot.paramMap;
        const recipeFromRoute = routeParams.get('id');
        this.recipeService
          .deleteRecipe(recipeFromRoute)
          .subscribe((res) => (this.recipe = res), err=>{
            console.log("error while getting r by id",recipeFromRoute,err);
            alert("Recipe has been deleted successfully!!");
            this.errorMsg = err;
          });
  }

}
