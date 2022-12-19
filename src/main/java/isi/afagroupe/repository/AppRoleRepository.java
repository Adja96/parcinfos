package isi.afagroupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isi.afagroupe.entities.AppRoleEntity;

public interface AppRoleRepository extends JpaRepository<AppRoleEntity, Integer> {
    AppRoleEntity findByNom(String nom);
}