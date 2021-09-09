package com.darwin.recipemanager.service.impl;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.RecipeList;
import com.darwin.recipemanager.mapper.RequestMapper;
import com.darwin.recipemanager.model.RecipeEntity;
import com.darwin.recipemanager.repository.RecipeDataRepository;
import com.darwin.recipemanager.service.RecipeDataService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeDataServiceImpl implements RecipeDataService {
    Logger log = LoggerFactory.getLogger(RecipeDataServiceImpl.class);
    private final RecipeDataRepository recipeDataRepository;
    private final RequestMapper requestMapper;

    /**
     *
     */
    @Override
    public InsertRecipeResponse saveRecipeData(InsertRecipeRequest request, String traceId) {
        log.info("Initiated saveRecipeData");
        String recipeDataId = UUID.randomUUID().toString();
        RecipeEntity recipeEntity = requestMapper.mapRecipeDataRequestToRecipeEntity(request, recipeDataId);

        RecipeEntity entity = null;
        long startTime = System.currentTimeMillis();
        try {
            entity = recipeDataRepository.save(recipeEntity);
            long endTime = System.currentTimeMillis();
            log.info("Recipe Data inserted Successfully within time: "+ (endTime-startTime));
        } catch(DataAccessException | ConstraintViolationException e) {
            log.error("An error occurred during insertion of RecipeData", e);
            e.printStackTrace();
            throw e;
        }
        return requestMapper.mapRecipeDataEntityToResponse(entity);
    }

    @Override
    public RecipeList getAllRecipe(String traceId) {
        log.info("Initiated getAllRecipe "+traceId);
        RecipeList recipeList = new RecipeList();
        List<RecipeEntity> recipeEntities = new ArrayList<>();

        try {
            recipeDataRepository.findAll().forEach(recipeEntity -> {
                RecipeEntity recipe = new RecipeEntity();
                recipe.setRecipeName(recipeEntity.getRecipeName());
                recipe.setRecipeDataId(recipeEntity.getRecipeDataId());
                recipe.setNatureOfRecipe(recipeEntity.getNatureOfRecipe());
                recipe.setInstructionEntities(recipeEntity.getInstructionEntities());
                recipe.setIngredientsEntities(recipeEntity.getIngredientsEntities());
                recipe.setQuantity(recipeEntity.getQuantity());
                recipe.setCreatedDate(recipeEntity.getCreatedDate());
                recipe.setModifiedDate(recipeEntity.getModifiedDate());
                recipeEntities.add(recipe);
            });
        log.info("Recipes found successfully "+traceId);
        List<InsertRecipeResponse> insertRecipeResponses = new ArrayList<>();
            recipeEntities.forEach(recipeEntity -> {
                insertRecipeResponses.add(requestMapper.mapRecipeDataEntityToResponse(recipeEntity));
            });
        recipeList.setItems(insertRecipeResponses);
        return recipeList;
        } catch (DataAccessException | NoSuchElementException e) {
            log.error("An error occurred during fetch all Recipes "+traceId,e);
            e.printStackTrace();
            throw e;
        }
    }

    public InsertRecipeResponse getRecipeById(String traceId, String recipeId) {
        log.info("Initiated getReRecipeById "+traceId);
        try {
            Optional<RecipeEntity> recipeEntity = recipeDataRepository.findById(recipeId);
            log.info("Recipe found successfully for id: "+recipeId+" traceId "+traceId);
            return requestMapper.mapRecipeDataEntityToResponse(recipeEntity.get());
        } catch (DataAccessException |NoSuchElementException e) {
            log.error("An error occurred during fetch recipe by id: "+recipeId, e);
            e.printStackTrace();
            throw e;
        }
    }

    public Void deleteRecipeDataById(String traceId, String recipeId) {
        log.info("Initiated deleteRecipeById");
        try {
            recipeDataRepository.deleteById(recipeId);
            log.info("Recipe has been successfully deleted!"+recipeId);
        } catch (DataAccessException | NoSuchElementException e) {
            log.error("An error occurred during delete the recipe "+recipeId);
            e.printStackTrace();
            throw e;
        }
        return null;
    }
}
