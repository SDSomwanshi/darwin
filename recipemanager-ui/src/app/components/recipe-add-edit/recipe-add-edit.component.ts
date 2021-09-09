import { Ingredients, Instructions, Receipe } from './../../models/receipe';
import { Component, OnInit } from '@angular/core';
//import { FormGroup, FormBuilder, FormArray } from  '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-recipe-add-edit',
  templateUrl: './recipe-add-edit.component.html',
  styleUrls: ['./recipe-add-edit.component.css']
})
export class RecipeAddEditComponent implements OnInit {
    errorMsg: any;
    recipe = new Receipe();
  
    currentIngredient: string;
    currentInstruction: string;
    typeArr: Array<Object> = [{name:"VEG"}, {name: "NONVEG"}];
    instructioins: Instructions[] = [];
    ingredients: Ingredients[] = [];
    selectedType = this.typeArr[0];
  constructor(private router: Router, private recipeService: RecipeService, private route: ActivatedRoute) {
     
  }

  ngOnInit(): void {
      this.recipe.ingredients = [];
      this.recipe.instructions = [];
      //this.recipe.recipeNature = this.selectedType;
  }

  submitRecipe() {
       // console.log("entered: ",this.contactForm.value);
        this.recipeService
                  .addRecipes(this.recipe)
                  .subscribe((res) => {
                    alert("Inserted Successfully!");
                    this.router.navigate(['/']);
                  }, err=>{
                    alert("Recipe has been deleted successfully!!");
                    this.errorMsg = err;
                  });
  }

  

    updateInstructionList() {
        var instruction = new Instructions();
        instruction.instructionName = this.currentInstruction;
        this.recipe.instructions.push(instruction);
    }
    updateIngredientList() {
        //this.ingredients.push(this.currentIngredient);
        var ingrident = new Ingredients();
        ingrident.name = this.currentIngredient;
        this.recipe.ingredients.push(ingrident);
        console.log(this.recipe.ingredients);
    }
}
