package isi.afagroupe.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import isi.afagroupe.entities.AppRolesEntity;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import isi.afagroupe.repository.AppRolesRepository;
import isi.afagroupe.dto.AppRoles;
import isi.afagroupe.exception.EntityNotFoundException;
import isi.afagroupe.exception.RequestException;
import isi.afagroupe.mapper.AppRolesMapper;

@Service
@AllArgsConstructor
public class AppRolesService {

    private AppRolesRepository appRolesRepository;
    private AppRolesMapper appRolesMapper;
    private MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<AppRoles> getAppRoles() {
        return StreamSupport.stream(appRolesRepository.findAll().spliterator(), false)
                .map(appRolesMapper::toAppRoles)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppRoles getAppRole(Integer id) {
        return appRolesMapper.toAppRoles(appRolesRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(messageSource.getMessage("appRolesId.notfound", new Object[]{id}, Locale.getDefault()))
                )
        );
    }

    @Transactional(readOnly = true)
    public AppRoles getAppRole(String nom) {
        return appRolesMapper.toAppRoles(Optional.ofNullable(appRolesRepository.findByNom(nom))
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("appRolesNom.notfound", new Object[]{nom},
                                Locale.getDefault()))));
    }

    @Transactional
    public AppRoles createAppRole(AppRoles appRoles) {
        return appRolesMapper.toAppRoles(appRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
    }

    @Transactional
    public AppRoles updateAppRole(Integer id, AppRoles appRoles) {
        return appRolesRepository.findById(id)
                .map(entity -> {
                    appRoles.setId(id);
                    return appRolesMapper.toAppRoles(appRolesRepository.save(appRolesMapper.fromAppRoles(appRoles)));
                })
                .orElseThrow(
                        () -> new EntityNotFoundException(messageSource.getMessage("appRolesId.notfound", new Object[]{id}, Locale.getDefault()))
                );
    }

    @Transactional
    public void deleteAppRole(Integer id) {
        try {
            appRolesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("appRolesId.errordeletion", new Object[] {id},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        }
    }
}
