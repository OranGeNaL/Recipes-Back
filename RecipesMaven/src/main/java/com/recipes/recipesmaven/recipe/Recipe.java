package com.recipes.recipesmaven.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recipes.recipesmaven.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
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

    @Column(nullable = false)
    private int timeToComplete;

    @Column(nullable = false)
    private int numberOfPortion;

    @Immutable
    @ManyToOne(optional = false)
    private User author;


}