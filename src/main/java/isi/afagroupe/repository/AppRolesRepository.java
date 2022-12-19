package isi.afagroupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isi.afagroupe.entities.AppRolesEntity;

public interface AppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {
    AppRolesEntity findByNom(String nom);
}