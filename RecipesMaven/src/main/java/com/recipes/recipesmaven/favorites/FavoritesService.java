package com.recipes.recipesmaven.favorites;

import com.recipes.recipesmaven.recipe.Recipe;
import com.recipes.recipesmaven.recipe.RecipeNotFoundException;
import com.recipes.recipesmaven.recipe.RecipeRepository;
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

    public void saveFavorite(Favorite favorite) {
        favoritesRepository.save(favorite);
    }

    public Iterator<Recipe> getAllByAuthor(String sesID) {
        Iterable<Favorite> favorites = favoritesRepository.findAllBySesAuthorIgnoreCaseOrderById(sesID);
        List<Recipe> recipes = new ArrayList<>();
        for (Favorite favorite : favorites) {
            recipes.add(recipeRepository.findById(favorite.getIdRecipe()).orElseThrow(RecipeNotFoundException::new));
        }
        return recipes.iterator();
    }
}
