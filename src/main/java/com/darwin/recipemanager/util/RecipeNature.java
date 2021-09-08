package com.darwin.recipemanager.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum RecipeNature {
    VEG(0),
    NONVEG(1);
    private final Integer value;
}
