package com.recipes.recipesmaven.favorites;

import com.recipes.recipesmaven.recipe.Recipe;
import com.recipes.recipesmaven.recipe.RecipeNotFoundException;
import com.recipes.recipesmaven.recipe.RecipeRepository;
import com.recipes.recipesmaven.users.NoSuchUserException;
import com.recipes.recipesmaven.users.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final RecipeRepository recipeRepository;
    private final SessionService sessionService;

    public void saveFavorite(Long idRecipe, String sesID) {
        String email = sessionService.validateUser(sesID);
        if (email == null) {
            throw new NoSuchUserException("No such user");
        }
        if (favoritesRepository.existsByEmail(email)) {
            Favorite favorite = favoritesRepository.findByEmail(email);
            if (favorite.getRecipes().contains(idRecipe)) {
                favorite.getRecipes().remove(idRecipe);
            } else {
                favorite.getRecipes().add(idRecipe);
            }
            favoritesRepository.save(favorite);
        } else {
            Favorite favorite = new Favorite();
            List<Long> idRecipes = new ArrayList<>();
            idRecipes.add(idRecipe);
            favorite.setEmail(email);
            favorite.setRecipes(idRecipes);
            favoritesRepository.save(favorite);
        }
    }

    public Iterator<Recipe> getAllByAuthor(String sesID) {
        String user = sessionService.validateUser(sesID);
        if (user == null) {
            throw new NoSuchUserException("No such user");
        }
        Favorite email = favoritesRepository.findByEmail(user);
        if (email == null) {
            throw new NoSuchUserException("This user doesn't have any favorites");
        }
        Iterable<Long> idRecipes = email.getRecipes();
        List<Recipe> recipes = new ArrayList<>();
        for (Long id: idRecipes) {
            recipes.add(recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("No such recipe")));
        }
        return recipes.iterator();
    }
}
