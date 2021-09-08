package com.darwin.recipemanager.service;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;

public interface RecipeDataService {
    InsertRecipeResponse saveRecipeData(InsertRecipeRequest request, String traceId);
}
