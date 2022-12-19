package isi.afagroupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isi.afagroupe.entities.AppUserEntity;

public interface AppUserRepository extends JpaRepository<AppUserEntity,Integer> {
    AppUserRepository findByEmail(String email);
}
