package isi.afagroupe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = false ,length = 150)
    private String nom;
    @Column( nullable = false ,length = 200)
    private String prenom;
    @Column( nullable = false ,length = 200)
    private String email;
    @Column( nullable = false ,length = 200)
    private String password;
    @ManyToMany
    private List<AppRoleEntity> appRole;
}
