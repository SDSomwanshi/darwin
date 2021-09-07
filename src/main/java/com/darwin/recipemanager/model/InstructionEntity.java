package com.darwin.recipemanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTIONS_DATA")
@Getter
@Setter
public class InstructionEntity extends BaseEntity implements Persistable<String> {
    @Id
    @Column(name = "INSTRUCTION_DATA_ID")
    private String instructionDataId;

    @Column(name = "INSTRUCTION_NAME")
    private String instruction;

    @ManyToOne
    @MapsId("recipeEntity")
    @JoinColumn(name = "RECIPE_DATA_ID", referencedColumnName = "RECIPE_DATA_ID")
    private RecipeEntity recipeEntity;

    @Override
    public String getId(){
        return instructionDataId;
    }

    @Override
    public boolean isNew(){
        return true;
    }
}
