package com.darwin.recipemanager.service.impl;

import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.mapper.RequestMapper;
import com.darwin.recipemanager.model.RecipeEntity;
import com.darwin.recipemanager.repository.RecipeDataRepository;
import com.darwin.recipemanager.service.RecipeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

@Service
public class RecipeDataServiceImpl implements RecipeDataService {
    @Autowired
    RecipeDataRepository recipeDataRepository;
    @Autowired
    RequestMapper requestMapper;

    @Override
    public InsertRecipeResponse saveRecipeData(InsertRecipeRequest request, String traceId) {
        String recipeDataId = UUID.randomUUID().toString();
        RecipeEntity recipeEntity = requestMapper.mapRecipeDataRequestToRecipeEntity(request, recipeDataId);

        RecipeEntity entity = null;
        long startTime = System.currentTimeMillis();
        try {
            entity = recipeDataRepository.save(recipeEntity);
            long endTime = System.currentTimeMillis();
        } catch(DataAccessException | ConstraintViolationException e) {
            e.printStackTrace();
        }
        return requestMapper.mapRecipeDataEntityToResponse(entity);
    }
}
