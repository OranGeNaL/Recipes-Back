package com.recipes.recipesmaven.session;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class SessionId {
    @Id
    private String session;

    @NotBlank
    @Column(nullable = false)
    private String nameUser;

    public SessionId(String nameUser) {
        this.session = UUID.randomUUID().toString();
        this.nameUser = nameUser;
    }
}
