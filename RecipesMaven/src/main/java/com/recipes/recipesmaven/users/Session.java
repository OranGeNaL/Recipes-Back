package com.recipes.recipesmaven.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @NotBlank
    private String sessID;

    @Id
    @NotBlank
    private String email;

}
