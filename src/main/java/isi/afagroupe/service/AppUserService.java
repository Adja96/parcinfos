package isi.afagroupe.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isi.afagroupe.dto.AppUser;
import isi.afagroupe.mapper.AppUserMapper;
import isi.afagroupe.repository.AppUserRepository;
import java.util.Locale;
import isi.afagroupe.exception.EntityNotFoundException;
import isi.afagroupe.exception.RequestException;


@Service
@AllArgsConstructor
public class AppUserService {
    private AppUserRepository appUserRepository;
    private AppUserMapper appUserMapper;
    private MessageSource messageSource;


    @Transactional(readOnly = true)
    public Page<AppUser> getAppUsers(Pageable pageable) {
        return appUserRepository.findAll(pageable).map(appUserMapper::toAppUser);
    }
    @Transactional(readOnly = true)
    public AppUser getAppUser(int id) {
        return appUserMapper.toAppUser(appUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    @Transactional
    public AppUser createAppUser(AppUser appUser) {
        appUserRepository.findById(appUser.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("client.exists", new Object[]{appUser.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(appUser)));
    }
    @Transactional
    public AppUser updateAppUser(int id, AppUser appUser){
        return appUserRepository.findById(id)
                .map(entity -> {
                    appUser.setId(id);
                    return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }
    @Transactional
    public void deleteAppUser(int id) {
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }


}
