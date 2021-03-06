package com.recipes.recipesmaven.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @UpdateTimestamp
    private LocalDateTime date;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Column(length = 64)
    private String mainPicture;

    @NotNull
    @Column(nullable = false)
    private int cookingDuration;

    @NotNull
    @Column(nullable = false)
    private int portionNumber;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    @Column(nullable = false)
    private List<String> ingredients;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    @Column(nullable = false)
    private List<String> directions;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    @Column(nullable = false)
    private List<String> stepsPicture;

    @NotBlank
    @Column(nullable = false)
    private String author;

    @NotNull
    @Column(nullable = false)
    private int views;

    @NotNull
    @Column(nullable = false)
    private long likes;

    @NotNull
    @Column(nullable = false)
    private long dislikes;

    @Column
    private boolean isLike;

    @Column
    private boolean isDislike;

    @Column
    private boolean isFavorite;
    @Transient
    public String getMainPhotosImagePath() {
        if (mainPicture == null || id == null) return null;

        return "/recipe-photos/main/" + id + "/" + mainPicture;
    }

    @Transient
    public List<String> getStepsPhotosImagePath() {
        if (stepsPicture == null || id == null) return null;
        List<String> res = new ArrayList<>();
        for (String steps: stepsPicture) {
            res.add("/recipe-photos/stepsPhoto/" + id + "/" + steps);
        }
        return res;
    }
}
