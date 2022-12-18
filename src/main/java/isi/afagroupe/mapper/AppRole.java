package isi.afagroupe.mapper;

import isi.afagroupe.entities.AppRoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppRole {
    AppRole toAppRole(AppRoleEntity appRole);

    AppRoleEntity fromAppRole(AppRole appRole);
}
