package isi.afagroupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUserRepository,Integer> {
    AppUserRepository findByEmail(String email);
}
