package com.darwin.recipemanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RECIPE_DATA")
@Getter
@Setter
public class RecipeEntity extends BaseEntity implements Persistable<String> {
    @Id
    @Column(name = "RECIPE_DATA_ID")
    private String recipeDataId;

    @Column(name = "RECIPE_NAME")
    private String recipeName;

    @Column(name = "RECIPE_NATURE")
    private int natureOfRecipe;

    @Column(name = "RECIPE_QTY")
    private int quantity;

    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IngredientEntity> ingredientsEntities;

    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InstructionEntity> instructionEntities;


    @Override
    public String getId(){
        return recipeDataId;
    }

    @Override
    public boolean isNew(){
        return true;
    }

}
