package com.darwin.recipemanager.api;

import com.darwin.recipemanager.api.util.TestDataFactory;
import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.service.RecipeDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class RecipeDataApiDelegateImplTest {

    RecipeDataApiDelegateImpl recipeDataApiDelegate;
    @Mock
    RecipeDataService recipeDataService;

    @Before
    public void setUp() {
        recipeDataApiDelegate = new RecipeDataApiDelegateImpl(recipeDataService);
    }

   /* @Test
    public void saveRecipeDataTest() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();

        ResponseEntity<InsertRecipeResponse> response = recipeDataApiDelegate.saveRecipe(recipeRequest, traceId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(recipeRequest.getRecipeName(), response.getBody().getRecipeName());
        //Assert.assertEquals(recipeRequest.getRecipeQty(), response.getBody().get);
    }*/
}
