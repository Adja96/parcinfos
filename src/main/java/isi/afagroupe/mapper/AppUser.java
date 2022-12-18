package isi.afagroupe.mapper;

import isi.afagroupe.entities.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppUser {
    AppUser toAppUser(AppUserEntity appUser);

    AppUserEntity fromAppUser(AppUser appUser);
}
