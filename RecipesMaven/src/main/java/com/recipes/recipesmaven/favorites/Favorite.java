package com.recipes.recipesmaven.favorites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Favorite {

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;

    @NotNull
    @ElementCollection
    @Column
    private List<Long> recipes;

    @NotBlank
    @Column(nullable = false)
    private String email;
}
