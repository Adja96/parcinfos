package isi.afagroupe.mapper;

import isi.afagroupe.dto.AppRole;
import isi.afagroupe.entities.AppRoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppRoleMapper {
    AppRole toAppRole(AppRoleEntity appRoleEntity);
    AppRoleEntity fromAppRole(AppRole appRole);
}
