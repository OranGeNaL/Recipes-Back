package com.recipes.recipesmaven.recipe;

import com.recipes.recipesmaven.LikeDislike.LikeDislikeService;
import com.recipes.recipesmaven.users.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final SessionService sessionService;
    private final LikeDislikeService likeDislikeService;

    public Long saveRecipe(Recipe recipe) {
        recipe.setAuthor(sessionService.validateUser(recipe.getAuthor()));
        return recipeRepository.save(recipe).getId();
    }

    public Recipe getRecipeById(Long id, String sesID) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("No such recipe"));
        recipe.setViews(recipe.getViews() + 1);
        recipe.setLikes(likeDislikeService.getRecipeLikes(id));
        recipe.setDislikes(likeDislikeService.getRecipeDisLikes(id));
        recipe.setLike(likeDislikeService.isLiked(id, sesID));
        recipe.setDislike(likeDislikeService.isDisliked(id, sesID));
        recipeRepository.save(recipe);
        return recipe;
    }

    public List<String> getRecipeByIdMainInformation(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("No such recipe"));
        return List.of(recipe.getName(), recipe.getMainPicture());
    }

    public void deleteRecipeById(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException("No such recipe");
        }
        recipeRepository.deleteById(id);
    }

    public void updateRecipeById(Long id, Recipe recipe) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException("No such recipe");
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

    public Iterable<Recipe> getAllRecipesByLikes() {
        return recipeRepository.findAllByOrderByLikesAsc();
    }
}