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

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import isi.afagroupe.exception.EntityNotFoundException;
import isi.afagroupe.exception.RequestException;


@Service
@AllArgsConstructor
public class AppUserService {
    private AppUserRepository appUserRepository;
    private AppUserMapper appUserMapper;
    private MessageSource messageSource;


    @Transactional(readOnly = true)
    public List<AppUser> getAppUsers() {
        return StreamSupport.stream(appUserRepository.findAll().spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppUser getAppUser(Integer id) {
        return appUserMapper.toAppUser(appUserRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("appUser.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional(readOnly = true)
    public AppUser getAppUserByEmail(String email) {
        return appUserMapper.toAppUser(Optional.ofNullable(appUserRepository.findByEmail(email))
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("appUserEmail.notfound", new Object[]{email},
                                Locale.getDefault()))));

    }

    @Transactional(readOnly = true)
    public List<AppUser> getAppUserByLastname(String lastname) {
        return StreamSupport.stream(Optional.ofNullable(appUserRepository.findByNom(lastname)).orElseThrow(() ->
                                new EntityNotFoundException(messageSource.getMessage("appUserNom.notfound", new Object[]{lastname}, Locale.getDefault())))
                        .spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppUser> getAppUserByFirstname(String firstname) {
        return StreamSupport.stream(Optional.ofNullable(appUserRepository.findByPrenom(firstname)).orElseThrow(() ->
                                new EntityNotFoundException(messageSource.getMessage("appUserNom.notfound", new Object[]{firstname}, Locale.getDefault())))
                        .spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
    }

    @Transactional
    public AppUser createAppUser(AppUser AppUser) {
        return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(AppUser)));
    }

    @Transactional
    public AppUser updateAppUser(Integer id, AppUser AppUser) {
        return appUserRepository.findById(id)
                .map(entity -> {
                    AppUser.setId(id);
                    return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(AppUser)));
                })
                .orElseThrow(
                        () -> new EntityNotFoundException(messageSource.getMessage("appUser.notfound", new Object[]{id}, Locale.getDefault()))
                );
    }

    @Transactional
    public void deleteAppUser(Integer id) {
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("appUser.errordeletion", new Object[] {id},
                    Locale.getDefault()), HttpStatus.CONFLICT);
        }
    }
}
