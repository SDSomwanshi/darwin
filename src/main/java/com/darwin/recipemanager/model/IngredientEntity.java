package com.darwin.recipemanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Table(name = "INGREDIENT_DATA")
@Getter
@Setter
@Entity
public class IngredientEntity extends BaseEntity implements Persistable<String> {

    @Id
    @Column(name = "INGREDIENT_DATA_ID")
    private String ingredientDataId;

    @Column(name = "INGREDIENT_NAME")
    private String ingredientName;

    @ManyToOne
    @MapsId("recipeEntity")
    @JoinColumn(name = "RECIPE_DATA_ID", referencedColumnName = "RECIPE_DATA_ID")
    private RecipeEntity recipeEntity;

    @Override
    public String getId(){
        return ingredientDataId;
    }

    @Override
    public boolean isNew(){
        return true;
    }
}
