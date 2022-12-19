package isi.afagroupe.mapper;

import isi.afagroupe.dto.AppUser;
import isi.afagroupe.entities.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppUserMapper {
    AppUser toAppUser(AppUserEntity appUserEntity);
    AppUserEntity fromAppUser(AppUser appUser);
}
