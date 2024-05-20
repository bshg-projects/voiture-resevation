package com.example.voitureresevation.zsecurity.config;

import com.example.voitureresevation.zsecurity.permissions.RoleEnum;
import com.example.voitureresevation.zsecurity.permissions.resources.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.http.HttpMethod.*;

public class RequestConfig {
    private RequestConfig() {
    }

    private static HttpSecurity http;

    public static void set(HttpSecurity http) throws Exception {
        if (RequestConfig.http == null) RequestConfig.http = http;
        contratAuthorizeHttpRequests();
        administrateurAuthorizeHttpRequests();
        clientAuthorizeHttpRequests();
        reservationAuthorizeHttpRequests();
        offreAuthorizeHttpRequests();
        voitureAuthorizeHttpRequests();
        appUserAuthorizeHttpRequests();
        roleAuthorizeHttpRequests();
        defaultAuthorizeHttpRequests();
    }

    private static void appUserAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/app-user/**").hasAnyAuthority(UserPermission.READ.authority())
                .requestMatchers(POST, "/api/app-user").permitAll()
                .requestMatchers(PUT, "/api/app-user/**").hasAnyAuthority(UserPermission.EDIT.authority())
                .requestMatchers(PUT, "/api/app-user/**").hasAnyAuthority(UserPermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/app-user/**").hasAnyAuthority(UserPermission.DELETE.authority())
                .requestMatchers("/api/app-user/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void roleAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/app-role/**").hasAnyAuthority(RolePermission.READ.authority())
                .requestMatchers(POST, "/api/app-role/**").hasAnyAuthority(RolePermission.CREATE.authority())
                .requestMatchers(PUT, "/api/app-role/**").hasAnyAuthority(RolePermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/app-role/**").hasAnyAuthority(RolePermission.DELETE.authority())
                .requestMatchers("/api/app-role/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void contratAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/contrat/**").hasAnyAuthority(ContratPermission.READ.authority())
                .requestMatchers(POST, "/api/contrat/**").hasAnyAuthority(ContratPermission.CREATE.authority())
                .requestMatchers(PUT, "/api/contrat/**").hasAnyAuthority(ContratPermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/contrat/**").hasAnyAuthority(ContratPermission.DELETE.authority())
                .requestMatchers("/api/contrat/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void administrateurAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/administrateur/**").hasAnyAuthority(AdministrateurPermission.READ.authority())
                .requestMatchers(POST, "/api/administrateur/**").hasAnyAuthority(AdministrateurPermission.CREATE.authority())
                .requestMatchers(PUT, "/api/administrateur/**").hasAnyAuthority(AdministrateurPermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/administrateur/**").hasAnyAuthority(AdministrateurPermission.DELETE.authority())
                .requestMatchers("/api/administrateur/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void clientAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/client/**").hasAnyAuthority(ClientPermission.READ.authority())
                .requestMatchers(POST, "/api/client/**").hasAnyAuthority(ClientPermission.CREATE.authority())
                .requestMatchers(PUT, "/api/client/**").hasAnyAuthority(ClientPermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/client/**").hasAnyAuthority(ClientPermission.DELETE.authority())
                .requestMatchers("/api/client/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void reservationAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/reservation/**").hasAnyAuthority(ReservationPermission.READ.authority())
                .requestMatchers(POST, "/api/reservation/**").hasAnyAuthority(ReservationPermission.CREATE.authority())
                .requestMatchers(PUT, "/api/reservation/**").hasAnyAuthority(ReservationPermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/reservation/**").hasAnyAuthority(ReservationPermission.DELETE.authority())
                .requestMatchers("/api/reservation/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void offreAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/offre/**").hasAnyAuthority(OffrePermission.READ.authority())
                .requestMatchers(POST, "/api/offre/**").hasAnyAuthority(OffrePermission.CREATE.authority())
                .requestMatchers(PUT, "/api/offre/**").hasAnyAuthority(OffrePermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/offre/**").hasAnyAuthority(OffrePermission.DELETE.authority())
                .requestMatchers("/api/offre/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void voitureAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(GET, "/api/voiture/**").hasAnyAuthority(VoiturePermission.READ.authority())
                .requestMatchers(POST, "/api/voiture/**").hasAnyAuthority(VoiturePermission.CREATE.authority())
                .requestMatchers(PUT, "/api/voiture/**").hasAnyAuthority(VoiturePermission.EDIT.authority())
                .requestMatchers(DELETE, "/api/voiture/**").hasAnyAuthority(VoiturePermission.DELETE.authority())
                .requestMatchers("/api/voiture/**").hasAnyRole(RoleEnum.ADMIN.name())
        );
    }

    private static void defaultAuthorizeHttpRequests() throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated());
    }
}