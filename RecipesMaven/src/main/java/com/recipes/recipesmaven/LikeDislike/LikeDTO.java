package com.recipes.recipesmaven.LikeDislike;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class LikeDTO {
    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    @ElementCollection
    @Column
    private List<String> email;

    @NotNull
    @Column(nullable = false)
    private Long idRecipe;

    @NotNull
    @Column(nullable = false)
    private Long likes;
}
