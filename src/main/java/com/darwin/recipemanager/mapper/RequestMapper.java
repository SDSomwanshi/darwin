package com.darwin.recipemanager.mapper;

import com.darwin.recipemanager.generated.model.Ingredient;
import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.Instruction;
import com.darwin.recipemanager.model.IngredientEntity;
import com.darwin.recipemanager.model.InstructionEntity;
import com.darwin.recipemanager.model.RecipeEntity;
import com.darwin.recipemanager.util.RecipeNature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    public RecipeEntity mapRecipeDataRequestToRecipeEntity(InsertRecipeRequest recipeRequest, String recipeDataId) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeDataId(recipeDataId);
        recipeEntity.setRecipeName(recipeRequest.getRecipeName());
        recipeEntity.setNatureOfRecipe(RecipeNature.valueOf(recipeRequest.getRecipeType()).getValue());
        recipeEntity.setIngredientsEntities(getIngredientData(recipeRequest.getIngredients(), recipeDataId));
        recipeEntity.setInstructionEntities(getInstructionData(recipeRequest.getInstructions(), recipeDataId));
        return recipeEntity;
    }

    public InsertRecipeResponse mapRecipeDataEntityToResponse(RecipeEntity entity) {
        InsertRecipeResponse recipeResponse = new InsertRecipeResponse();
        recipeResponse.setRecipeName(entity.getRecipeName());
        recipeResponse.setRecipeNature(entity.getNatureOfRecipe() == 0 ? InsertRecipeResponse.RecipeNatureEnum.valueOf("VEG") : InsertRecipeResponse.RecipeNatureEnum.valueOf("NONVEG"));
        recipeResponse.setIngredients(getIngredients(entity.getIngredientsEntities()));
        recipeResponse.setInstructions(getInstructions(entity.getInstructionEntities()));
        recipeResponse.setCreatedDate(entity.getCreatedDate().toString());
        recipeResponse.setUpdatedDate(entity.getModifiedDate().toString());
        return recipeResponse;
    }

    private List<Instruction> getInstructions(List<InstructionEntity> instructionEntityList) {
        List<Instruction> instructions = new ArrayList<>();
        instructionEntityList.forEach(instructionEntity -> {
            Instruction instruction = new Instruction();
            instruction.setInstructionName(instructionEntity.getInstructionName());
            instructions.add(instruction);
        });
        return instructions;
    }

    private List<Ingredient> getIngredients(List<IngredientEntity> ingredientEntityList) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientEntityList.forEach(ingredientEntity -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientEntity.getIngredientName());
            ingredients.add(ingredient);
        });
        return ingredients;
    }

    private List<IngredientEntity> getIngredientData(List<Ingredient> ingredientList, String recipeDataId) {
        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        ingredientList.forEach( ingredient -> {
            IngredientEntity ingredientEntity = new IngredientEntity();
            ingredientEntity.setIngredientDataId(UUID.randomUUID().toString());
            ingredientEntity.setIngredientName(ingredient.getName());
            ingredientEntity.setRecipeDataId(recipeDataId);
            ingredientEntities.add(ingredientEntity);
        });
        return ingredientEntities;
    }

    private List<InstructionEntity> getInstructionData(List<Instruction> instructionList, String recipeDataId) {
        List<InstructionEntity> instructionEntities = new ArrayList<>();
        instructionList.forEach( instruction -> {
            InstructionEntity instructionEntity = new InstructionEntity();
            instructionEntity.setInstructionDataId(UUID.randomUUID().toString());
            instructionEntity.setInstructionName(instruction.getInstructionName());
            instructionEntity.setRecipeDataId(recipeDataId);
            instructionEntities.add(instructionEntity);
        });
        return instructionEntities;
    }
}
