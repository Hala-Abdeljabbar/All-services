package org.sid.securityservice.service;

import org.sid.securityservice.entities.AppRole;
import org.sid.securityservice.entities.AppUser;
import org.sid.securityservice.repo.AppRoleRepository;
import org.sid.securityservice.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {

        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser user = appUserRepository.findByUsername(userName);
        AppRole role = appRoleRepository.findByRolename(roleName);
        user.getAppRoles().add(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {


        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {

        return appUserRepository.findAll();
    }
}
