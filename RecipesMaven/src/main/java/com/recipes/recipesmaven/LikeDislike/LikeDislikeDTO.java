package com.recipes.recipesmaven.LikeDislike;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class LikeDislikeDTO {
    @Id
    @JsonIgnore
    @GeneratedValue
    Long id;

    @NotNull
    @Column(nullable = false)
    Long idRecipe;

    @NotNull
    @Column(nullable = false)
    Long likes;

    @NotNull
    @Column(nullable = false)
    Long dislikes;
}
