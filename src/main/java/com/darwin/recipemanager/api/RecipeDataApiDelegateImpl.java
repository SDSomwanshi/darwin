package com.darwin.recipemanager.api;

import com.darwin.recipemanager.generated.api.RecipeDataApiDelegate;
import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.RecipeList;
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

        return new ResponseEntity<>(recipeDataService.saveRecipeData(body, traceId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RecipeList> getAllRecipeData(String traceId) {

        return new ResponseEntity<>(recipeDataService.getAllRecipe(traceId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InsertRecipeResponse> getRecipeData(String traceId, String recipeId) {
        return new ResponseEntity<>(recipeDataService.getRecipeById(traceId, recipeId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(String traceId, String recipeId) {
        return new ResponseEntity<>(recipeDataService.deleteRecipeDataById(traceId, recipeId), HttpStatus.OK);
    }

    public ResponseEntity<InsertRecipeResponse> updateRecipe(InsertRecipeRequest body, String traceId,
                         String recipeId) {
        return new ResponseEntity<>(recipeDataService.updateRecipeData(body, traceId, recipeId), HttpStatus.OK);
    }
}
