package isi.afagroupe.controller;

import isi.afagroupe.dto.AppUser;
import isi.afagroupe.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AppUserController {
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAppUsers(){
        return appUserService.getAppUsers();
    }

    @GetMapping("/id/{id}")
    public AppUser getAppUser(@PathVariable("id") int id){
        return appUserService.getAppUser(id);
    }

    @GetMapping("/prenom/{prenom}")
    public List<AppUser> getAppUserByFirstname(@PathVariable("prenom") String fname){
        return appUserService.getAppUserByFirstname(fname);
    }
    @GetMapping("/nom/{nom}")
    public List<AppUser> getAppUserByLastname(@PathVariable("nom") String lname){
        return appUserService.getAppUserByLastname(lname);
    }

    @GetMapping("/email/{email}")
    public AppUser getAppUserByEmail(@PathVariable("email") String email){
        return appUserService.getAppUserByEmail(email);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser){
        return appUserService.createAppUser(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable("id") int id, @Valid @RequestBody AppUser appUser){
        return appUserService.updateAppUser(id, appUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppUser(@PathVariable("id") int id){
        appUserService.deleteAppUser(id);
        return;
    }
}
    
