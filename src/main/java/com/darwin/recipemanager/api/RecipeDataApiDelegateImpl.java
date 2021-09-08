package com.darwin.recipemanager.api;

import com.darwin.recipemanager.generated.api.RecipeDataApiDelegate;
import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.service.RecipeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeDataApiDelegateImpl implements RecipeDataApiDelegate {

    private final RecipeDataService recipeDataService;

    @Override
    public ResponseEntity<InsertRecipeResponse> saveRecipe(InsertRecipeRequest body, String traceId) {

        return new ResponseEntity<>(recipeDataService.saveRecipeData(body, traceId), HttpStatus.OK);
    }
}
