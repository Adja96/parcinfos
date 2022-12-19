package isi.afagroupe.mapper;

import isi.afagroupe.dto.AppRoles;
import isi.afagroupe.entities.AppRolesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppRolesMapper {
    AppRoles toAppRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromAppRoles(AppRoles appRoles);
}
