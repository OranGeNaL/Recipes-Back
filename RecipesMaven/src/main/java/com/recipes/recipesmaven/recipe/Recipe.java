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
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Recipe {
    @JsonIgnore
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

    @NotBlank
    @Column(nullable = false)
    private String pathToTheMainPicture;

    @NotBlank
    @Column(nullable = false)
    private int cookingDuration;

    @NotBlank
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
    private List<String> pathToTheStepsPictures;

    @NotBlank
    @Column(nullable = false)
    private String author;

    @NotNull
    @Column(nullable = false)
    private int views;
}