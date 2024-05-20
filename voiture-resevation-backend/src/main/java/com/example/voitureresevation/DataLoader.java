package com.example.voitureresevation;

import com.example.voitureresevation.entity.*;
import com.example.voitureresevation.service.facade.*;
import com.example.voitureresevation.zsecurity.entity.AppUser;
import com.example.voitureresevation.zsecurity.entity.Role;
import com.example.voitureresevation.zsecurity.permissions.RoleEnum;
import com.example.voitureresevation.zsecurity.service.facade.AppUserService;
import com.example.voitureresevation.zsecurity.service.facade.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Value("${app.data-loader.load}")
    private boolean load;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (load) {
            System.out.println("Data Loading...");
            generatePermission();
            generateAdminAccount();
            generateContrat();
            generateAdministrateur();
            generateClient();
            generateReservation();
            generateOffre();
            generateVoiture();
            System.out.println("Data Loaded!");
        }
    }

    private void generatePermission() {
        for (RoleEnum role : RoleEnum.values()) {
            var roleEntity = role.getRole();
            roleService.save(roleEntity);
        }
    }

    private void generateAdminAccount() {
        AppUser user = new AppUser("admin");
        user.setPassword("123456");
        Role role = new Role();
        role.setName(RoleEnum.ADMIN.name());
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);
        appUserService.save(user);
        System.out.println("One Account For ADMIN role is created: 'admin' :: '123456'");
    }

    private void generateContrat() {
        for (int i = 0; i < 50; i++) {
            Contrat item = new Contrat();
            contratService.create(item);
        }
        System.out.println("Data For Contrat Generated!");
    }

    private void generateAdministrateur() {
        for (int i = 0; i < 50; i++) {
            Administrateur item = new Administrateur();
            item.setNom("nom " + i);
            item.setPrenom("prenom " + i);
            item.setLocalite("localite " + i);
            item.setUsername("Administrateur " + i);
            item.setPassword(appUserService.cryptPassword("Administrateur " + i));
            List<Role> savedRoles = roleService.save(item.getRoles());
            item.setRoles(savedRoles);
            administrateurService.create(item);
        }
        System.out.println("Data For Administrateur Generated!");
    }

    private void generateClient() {
        for (int i = 0; i < 50; i++) {
            Client item = new Client();
            item.setNom("nom " + i);
            item.setPrenom("prenom " + i);
            item.setLocalite("localite " + i);
            item.setAdresse("adresse " + i);
            clientService.create(item);
        }
        System.out.println("Data For Client Generated!");
    }

    private void generateReservation() {
        for (int i = 0; i < 50; i++) {
            Reservation item = new Reservation();
            reservationService.create(item);
        }
        System.out.println("Data For Reservation Generated!");
    }

    private void generateOffre() {
        for (int i = 0; i < 50; i++) {
            Offre item = new Offre();
            item.setCode("code " + i);
            offreService.create(item);
        }
        System.out.println("Data For Offre Generated!");
    }

    private void generateVoiture() {
        for (int i = 0; i < 50; i++) {
            Voiture item = new Voiture();
            item.setMarque("marque " + i);
            item.setColeur("coleur " + i);
            item.setCategorie("categorie " + i);
            voitureService.create(item);
        }
        System.out.println("Data For Voiture Generated!");
    }

    @Autowired
    private ContratService contratService;
    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private OffreService offreService;
    @Autowired
    private VoitureService voitureService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private RoleService roleService;
}