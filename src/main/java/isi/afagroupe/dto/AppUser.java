package isi.afagroupe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private int id;
    @NotNull(message ="le nom ne doit pas etre null")
    private String nom;
    @NotNull(message ="le prenom ne doit pas etre null")
    private String prenom;
    @NotNull(message ="l'email ne doit pas etre null")
    private String email;
    @NotNull(message ="le password ne doit pas etre null")
    private String password;
}
