package isi.afagroupe.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import isi.afagroupe.entities.AppRoleEntity;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import isi.afagroupe.repository.AppRoleRepository;
import isi.afagroupe.dto.AppRole;
import isi.afagroupe.exception.EntityNotFoundException;
import isi.afagroupe.exception.RequestException;
import isi.afagroupe.mapper.AppRoleMapper;

@Service
@AllArgsConstructor
public class AppRoleService {

    private AppRoleRepository appRoleRepository;
    private AppRoleMapper appRoleMapper;
    private MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<AppRole> getAppRoles() {

        return StreamSupport.stream(appRoleRepository.findAll().spliterator(), false)
                .map(appRoleMapper::toAppRole)
                .collect(Collectors.toList());/**/

        /*List<AppRoleEntity> roleEntities = StreamSupport.stream(appRoleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<AppRole> dtoroles = new ArrayList<>();
        roleEntities.forEach(r -> {
            AppRole d = new AppRole();
            d.setId(r.getId());
            d.setNom(r.getNom());
            dtoroles.add(d);
        });


        for (AppRoleEntity dto : roleEntities){
            AppRole d = new AppRole();
            d.setId(dto.getId());
            d.setNom(dto.getNom());
            dtoroles.add(d);
        }
        return dtoroles;*/
    }

    @Transactional(readOnly = true)
    public AppRole getAppRole(int id) {
        return appRoleMapper.toAppRole(appRoleRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(messageSource.getMessage("appRoleId.notfound", new Object[]{id}, Locale.getDefault()))
                )
        );
    }

    @Transactional(readOnly = true)
    public AppRole getAppRoleByName(String name) {
        return appRoleMapper.toAppRole(Optional.ofNullable(appRoleRepository.findByNom(name))
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("appRoleNom.notfound", new Object[]{name},
                                Locale.getDefault()))));
    }

    @Transactional
    public AppRole createAppRole(AppRole AppRole) {
        AppRoleEntity app = appRoleMapper.fromAppRole(AppRole);
        System.out.print(app);
        return appRoleMapper.toAppRole(appRoleRepository.save(app));
    }

    @Transactional
    public AppRole updateAppRole(int id, AppRole appRole) {
        return appRoleRepository.findById(id)
                .map(entity -> {
                    appRole.setId(id);
                    return appRoleMapper.toAppRole(appRoleRepository.save(appRoleMapper.fromAppRole(appRole)));
                })
                .orElseThrow(
                        () -> new EntityNotFoundException(messageSource.getMessage("appRoleId.notfound", new Object[]{id}, Locale.getDefault()))
                );
    }

    @Transactional
    public void deleteAppRole(int id) {
        try {
            appRoleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("appRoleId.errordeletion", new Object[] {id},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        }
    }
}
