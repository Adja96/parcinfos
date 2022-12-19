package isi.afagroupe.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import isi.afagroupe.dto.AppUser;
import isi.afagroupe.service.AppUserService;

@RestController
@RequestMapping("/appuser")
public class AppUserController {
    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    
    }
    @GetMapping
    public Page<AppUser> getAppUsers(Pageable pageable) {
        return appUserService.getAppUsers(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUser> getAppUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(appUserService.getAppUser(id));

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser) {
            return appUserService.createAppUser(appUser); 
    }

    @PutMapping("{id}")
    //@IsAdmin
    public AppUser updateAppUser(@PathVariable("id") int id, @Valid @RequestBody AppUser appUser) {
            return appUserService.updateAppUser(id, appUser);
        }
        
    @DeleteMapping("{id}")
    public void deleteAppUser(@PathVariable("id") int id) {
        appUserService.deleteAppUser(id);
    }       
}        
    
