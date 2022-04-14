package com.recipes.recipesmaven.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @NotBlank
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8)
    private String password;
}
