package com.darwin.recipemanager.api;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.RecipeList;
import com.darwin.recipemanager.service.RecipeDataService;
import com.darwin.recipemanager.util.TestDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeDataApiDelegateImplTest {
    RecipeDataApiDelegateImpl recipeDataApiDelegate;
    @Mock
    RecipeDataService recipeDataService;

    @Before
    public void setUp() {
        recipeDataApiDelegate = new RecipeDataApiDelegateImpl(recipeDataService);
    }

    @Test
    public void saveRecipeDataTest() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();

        Mockito.when(recipeDataService.saveRecipeData(Mockito.any(), Mockito.any())).thenReturn(new InsertRecipeResponse());
        ResponseEntity<InsertRecipeResponse> response = recipeDataApiDelegate.saveRecipe(recipeRequest, traceId);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getAllRecipeDataTest() {
        String traceId = UUID.randomUUID().toString();

        Mockito.when(recipeDataService.getAllRecipe(Mockito.any())).thenReturn(new RecipeList());
        ResponseEntity<RecipeList> response = recipeDataApiDelegate.getAllRecipeData(traceId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getRecipeDataTest() {
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();
        Mockito.when(recipeDataService.getRecipeById(Mockito.any(), Mockito.any())).thenReturn(new InsertRecipeResponse());
        ResponseEntity<InsertRecipeResponse> response = recipeDataApiDelegate.getRecipeData(traceId, recipeId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void deleteRecipeDataTest() {
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();
        ResponseEntity<Void> response = recipeDataApiDelegate.delete(traceId, recipeId);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNull(response.getBody());
        Mockito.verify(recipeDataService, Mockito.atLeastOnce()).deleteRecipeDataById(traceId, recipeId);
    }
}
