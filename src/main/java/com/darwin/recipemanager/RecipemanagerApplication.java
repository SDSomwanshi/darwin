package com.darwin.recipemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.darwin.recipemanager"})
public class RecipemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipemanagerApplication.class, args);
	}

}
