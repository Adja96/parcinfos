package isi.afagroupe.service;

import isi.afagroupe.dto.AppRoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppRolesServiceTest {
    @Autowired
    private AppRolesService appRolesService;

    @Test
    void getAppRoles() {
        List<AppRoles> roles = appRolesService.getAppRoles();

        Assertions.assertNotNull(roles);
    }

    @Test
    void getAppRoleById() {
        AppRoles role = appRolesService.getAppRole(1);

        Assertions.assertNotNull(role);
    }

    @Test
    void getAppRoleByName() {
        AppRoles role = new AppRoles();
        role.setId(1);
        role.setNom("ROLE_SUPERADMIN");

        AppRoles role_saved = appRolesService.getAppRole("ROLE_SUPERADMIN");

        Assertions.assertEquals(role.getNom(), role_saved.getNom());
    }

    @Test
    void createAppRole() {
        AppRoles role = new AppRoles();
        //role.setNom("ROLE_SUPERADMIN");
        //role.setNom("ROLE_A_Modifier");
        role.setNom("ROLE_A_Supprimer");

        AppRoles role_save = appRolesService.createAppRole(role);

        Assertions.assertNotNull(role_save);
    }

    @Test
    void updateAppRole() {
        AppRoles role = appRolesService.getAppRole(2);
        role.setNom("Role_Modifie_Succes");

        AppRoles role_saved = appRolesService.updateAppRole(2, role);
        Assertions.assertEquals(role.getNom(), role_saved.getNom());
    }

    @Test
    void deleteAppRole() {
        appRolesService.deleteAppRole(3);

        Assertions.assertTrue(true);
    }
}