package com.recipes.recipesmaven.LikeDislike;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class DisLikeDTO {
    @Id
    @JsonIgnore
    @GeneratedValue
    Long id;

    @ElementCollection
    @Column
    List<String> email;

    @NotNull
    @Column(nullable = false)
    Long idRecipe;

    @NotNull
    @Column(nullable = false)
    Long dislikes;
}
