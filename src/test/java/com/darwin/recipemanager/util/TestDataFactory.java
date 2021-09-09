package com.darwin.recipemanager.util;

import com.darwin.recipemanager.generated.model.Ingredient;
import com.darwin.recipemanager.generated.model.InsertRecipeRequest;
import com.darwin.recipemanager.generated.model.InsertRecipeResponse;
import com.darwin.recipemanager.generated.model.Instruction;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TestDataFactory {
    public static final String TRACE_ID = "2629ea54-253a-4c3a-ac48-d772755da351";

    public static InsertRecipeRequest buildRecipeDataRequest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient()
                .name("Chicken");
        Ingredient ingredient2 = new Ingredient()
                .name("Butter");
        Ingredient ingredient3 = new Ingredient()
                .name("Coriander powder");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        List<Instruction> instructions = new ArrayList<>();
        Instruction instruction1 = new Instruction().instructionName("Add extra spice");
        Instruction instruction2 = new Instruction().instructionName("Add extra Butter");
        instructions.add(instruction1);
        instructions.add(instruction2);

        return new InsertRecipeRequest()
                .recipeName("Chicken Curry")
                .recipeType(InsertRecipeResponse.RecipeNatureEnum.NONVEG.toString())
                .recipeQty(4)
                .ingredients(ingredients)
                .instructions(instructions);
    }
}
