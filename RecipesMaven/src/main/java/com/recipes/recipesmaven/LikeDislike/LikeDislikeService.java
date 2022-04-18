package com.recipes.recipesmaven.LikeDislike;

import com.recipes.recipesmaven.recipe.RecipeNotFoundException;
import com.recipes.recipesmaven.users.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeDislikeService {

    private final LikeRepository likeRepository;
    private final SessionService sessionService;
    private final DislikeRepository dislikeRepository;

    public void likePost(Long idRecipe, String sesID) {
        String email = getEmail(sesID);
        LikeDTO temp = likeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        List<String> emailList = temp.getEmail();
        if (!temp.getEmail().contains(email)) {
            emailList.add(email);
            temp.setEmail(emailList);
            temp.setLikes(temp.getLikes() + 1);
        } else {
            emailList.remove(email);
            temp.setEmail(emailList);
            temp.setLikes(temp.getLikes() - 1);
        }
        likeRepository.save(temp);
    }

    public void dislikePost(Long idRecipe, String sesID) {
        String email = getEmail(sesID);
        DisLikeDTO temp = dislikeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        List<String> emailList = temp.getEmail();
        if (!temp.getEmail().contains(email)) {
            emailList.add(email);
            temp.setEmail(emailList);
            temp.setDislikes(temp.getDislikes() + 1);
        } else {
            emailList.remove(email);
            temp.setEmail(emailList);
            temp.setDislikes(temp.getDislikes() - 1);
        }
        dislikeRepository.save(temp);
    }

    public void save(Long idRecipe) {
        LikeDTO save = new LikeDTO();
        save.setIdRecipe(idRecipe);
        save.setLikes(0L);
        likeRepository.save(save);
        DisLikeDTO disLikeDTO = new DisLikeDTO();
        disLikeDTO.setIdRecipe(idRecipe);
        disLikeDTO.setDislikes(0L);
        dislikeRepository.save(disLikeDTO);
    }

    public boolean isLiked(Long idRecipe, String sesID) {
        String email = getEmail(sesID);
        LikeDTO temp = likeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        return temp.getEmail().contains(email);
    }

    public boolean isDisliked(Long idRecipe, String sesID) {
        String email = getEmail(sesID);
        DisLikeDTO temp = dislikeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new);
        return temp.getEmail().contains(email);
    }

    public Long getRecipeLikes(Long idRecipe) {
        return likeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new).getLikes();
    }

    public Long getRecipeDisLikes(Long idRecipe) {
        return dislikeRepository.findByIdRecipe(idRecipe).orElseThrow(RecipeNotFoundException::new).getDislikes();
    }
    private String getEmail(String sesID) {
        return sessionService.validateUser(sesID);
    }
}
