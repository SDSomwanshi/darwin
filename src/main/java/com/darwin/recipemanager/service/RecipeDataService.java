package com.darwin.recipemanager.service;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.RecipeList;


public interface RecipeDataService {
    InsertRecipeResponse saveRecipeData(InsertRecipeRequest request, String traceId);
    RecipeList getAllRecipe(String traceId);
    InsertRecipeResponse getRecipeById(String traceId, String recipeId);
    Void deleteRecipeDataById(String traceId, String recipeId);
}
