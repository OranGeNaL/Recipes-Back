package com.recipes.recipesmaven.favorites;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritesService {
    FavoritesRepository favoritesRepository;

    public Long saveFavorite(String sesID, Long idRecipe) {
        Favorite favorite = new Favorite();
        favorite.setIdRecipe(idRecipe);
        favorite.setSesAuthor(sesID);
        return favoritesRepository.save(favorite).getId();
    }

    public Iterable<Favorite> getAllByAuthor(String sesID) {
        return favoritesRepository.findAllBySesAuthorIgnoreCaseOrderById(sesID);
    }
}
