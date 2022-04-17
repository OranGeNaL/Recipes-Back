package com.recipes.recipesmaven.recipe;

import com.recipes.recipesmaven.users.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final SessionService sessionService;

    public Long saveRecipe(Recipe recipe) {
        recipe.setAuthor(sessionService.validateUser(recipe.getAuthor()));
        return recipeRepository.save(recipe).getId();
    }

    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        recipe.setViews(recipe.getViews() + 1);
        recipeRepository.save(recipe);
        return recipe;
    }

    public List<String> getRecipeByIdMainInformation(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
        return List.of(recipe.getName(), recipe.getMainPicture());
    }

    public void deleteRecipeById(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException();
        }
        recipeRepository.deleteById(id);
    }

    public void updateRecipeById(Long id, Recipe recipe) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException();
        }
        recipe.setId(id);
        recipeRepository.save(recipe);
    }

    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Iterable<Recipe> getAllRecipesOrderByDate() {
        return recipeRepository.findAllByOrderByDateAsc();
    }

    public Iterable<Recipe> getAllRecipesOrderByViews() {
        return recipeRepository.findAllByOrderByViewsAsc();
    }

    public Iterable<Recipe> getAllRecipesByAuthor(String sesID) {
        String login = sessionService.validateUser(sesID);
        return recipeRepository.findAllByAuthorIgnoreCase(login);
    }

    public Iterable<Recipe> getAllRecipesByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public Iterable<Recipe> getAllRecipesContainingName(String name) {
        return recipeRepository.findAllByNameContainsIgnoreCaseOrderByDateDesc(name);
    }
}