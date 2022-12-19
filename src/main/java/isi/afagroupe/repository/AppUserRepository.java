package isi.afagroupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isi.afagroupe.entities.AppUserEntity;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUserEntity,Integer> {
    AppUserEntity findByEmail(String email);
    List<AppUserEntity> findByNom(String nom);
    List<AppUserEntity> findByPrenom(String prenom);
}
