package com.darwin.recipemanager.repository;

import com.darwin.recipemanager.model.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RecipeDataRepository extends CrudRepository<RecipeEntity, String> {
}
