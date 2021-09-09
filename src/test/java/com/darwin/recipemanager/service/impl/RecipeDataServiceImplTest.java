package com.darwin.recipemanager.service.impl;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.RecipeList;
import com.darwin.recipemanager.mapper.RequestMapper;
import com.darwin.recipemanager.model.RecipeEntity;
import com.darwin.recipemanager.repository.RecipeDataRepository;
import com.darwin.recipemanager.util.RecipeNature;
import com.darwin.recipemanager.util.TestDataFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeDataServiceImplTest {

    @InjectMocks
    RecipeDataServiceImpl recipeDataService;

    @Mock
    RecipeDataRepository recipeDataRepository;
    @Mock
    RequestMapper requestMapper;

    @Before
    public void setUp() {
        recipeDataService = new RecipeDataServiceImpl(recipeDataRepository, requestMapper);
    }

    @Test
    public void positive_saveRecipeData() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setQuantity(recipeRequest.getRecipeQty());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());

        InsertRecipeResponse recipeResponse = new InsertRecipeResponse();
        recipeResponse.setRecipeId(recipeId);
        recipeResponse.setRecipeNature(recipeEntity.getNatureOfRecipe() == 0 ? InsertRecipeResponse.RecipeNatureEnum.valueOf("VEG") : InsertRecipeResponse.RecipeNatureEnum.valueOf("NONVEG"));
        recipeResponse.setRecipeName(recipeEntity.getRecipeName());

        Mockito.when(requestMapper.mapRecipeDataRequestToRecipeEntity(recipeRequest, recipeId)).thenReturn(recipeEntity);
        Mockito.when(recipeDataRepository.save(Mockito.any())).thenReturn(recipeEntity);
        Mockito.when(requestMapper.mapRecipeDataEntityToResponse(Mockito.any())).thenReturn(recipeResponse);

        InsertRecipeResponse recipeResponse1 = recipeDataService.saveRecipeData(recipeRequest, traceId);
        Assert.assertEquals(recipeRequest.getRecipeName(), recipeResponse1.getRecipeName());
        Assert.assertEquals(recipeId, recipeResponse1.getRecipeId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void negative_saveRecipeData() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setQuantity(recipeRequest.getRecipeQty());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());

        Mockito.when(requestMapper.mapRecipeDataRequestToRecipeEntity(recipeRequest, recipeId)).thenReturn(recipeEntity);
        Mockito.when(recipeDataRepository.save(Mockito.any())).thenThrow(new ConstraintViolationException("could not execute statement", new SQLException(), "PK"));

        recipeDataService.saveRecipeData(recipeRequest, traceId);
    }

    @Test
    public void positive_getAllRecipe() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setQuantity(recipeRequest.getRecipeQty());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());

        List<RecipeEntity> recipeEntities = new ArrayList<>();
        recipeEntities.add(recipeEntity);

        InsertRecipeResponse recipeResponse = new InsertRecipeResponse();
        recipeResponse.setRecipeId(recipeId);
        recipeResponse.setRecipeNature(recipeEntity.getNatureOfRecipe() == 0 ? InsertRecipeResponse.RecipeNatureEnum.valueOf("VEG") : InsertRecipeResponse.RecipeNatureEnum.valueOf("NONVEG"));
        recipeResponse.setRecipeName(recipeEntity.getRecipeName());

        Mockito.when(recipeDataRepository.findAll()).thenReturn(recipeEntities);
        Mockito.when(requestMapper.mapRecipeDataEntityToResponse(Mockito.any())).thenReturn(recipeResponse);

        RecipeList response = recipeDataService.getAllRecipe(traceId);
        Assert.assertEquals(recipeId, response.getItems().get(0).getRecipeId());
        Assert.assertEquals(recipeRequest.getRecipeName(), response.getItems().get(0).getRecipeName());
    }

    @Test(expected = NoSuchElementException.class)
    public void negative_getAllRecipe() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setQuantity(recipeRequest.getRecipeQty());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());

        List<RecipeEntity> recipeEntities = new ArrayList<>();
        recipeEntities.add(recipeEntity);

        Mockito.when(recipeDataRepository.findAll()).thenThrow(new NoSuchElementException());
        recipeDataService.getAllRecipe(traceId);
    }

    @Test
    public void positive_getRecipeById() {
        InsertRecipeRequest recipeRequest = TestDataFactory.buildRecipeDataRequest();
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setQuantity(recipeRequest.getRecipeQty());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());

        InsertRecipeResponse recipeResponse = new InsertRecipeResponse();
        recipeResponse.setRecipeId(recipeId);
        recipeResponse.setRecipeNature(recipeEntity.getNatureOfRecipe() == 0 ? InsertRecipeResponse.RecipeNatureEnum.valueOf("VEG") : InsertRecipeResponse.RecipeNatureEnum.valueOf("NONVEG"));
        recipeResponse.setRecipeName(recipeEntity.getRecipeName());

        Mockito.when(recipeDataRepository.findById(Mockito.anyString())).thenReturn(Optional.of(recipeEntity));
        Mockito.when(requestMapper.mapRecipeDataEntityToResponse(Mockito.any())).thenReturn(recipeResponse);

        InsertRecipeResponse response = recipeDataService.getRecipeById(traceId, recipeId);
        Assert.assertEquals(recipeId, response.getRecipeId());
        Assert.assertEquals(recipeRequest.getRecipeName(), response.getRecipeName());
    }

    @Test(expected = NoSuchElementException.class)
    public void negative_getRecipeById() {
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();

        Mockito.when(recipeDataRepository.findById(Mockito.anyString())).thenThrow(new NoSuchElementException());
        recipeDataService.getRecipeById(traceId, recipeId);
    }

    @Test
    public void positive_deleteRecipe() {
        String traceId = UUID.randomUUID().toString();
        String recipeId = UUID.randomUUID().toString();

        recipeDataService.deleteRecipeDataById(traceId,recipeId);
        Mockito.verify(recipeDataRepository,Mockito.atLeastOnce()).deleteById(Mockito.anyString());
    }

}
