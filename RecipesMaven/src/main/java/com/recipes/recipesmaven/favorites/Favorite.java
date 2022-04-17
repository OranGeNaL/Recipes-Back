package com.recipes.recipesmaven.favorites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Favorite {

    @Id
    @JsonIgnore
    @GeneratedValue
    Long id;

    @NotNull
    @Column(nullable = false)
    Long idRecipe;

    @NotBlank
    @Column(nullable = false)
    String sesAuthor;
}
