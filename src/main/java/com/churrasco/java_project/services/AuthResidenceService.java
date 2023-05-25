package com.churrasco.java_project.services;

import com.churrasco.java_project.models.AuthResidenceModel;
import com.churrasco.java_project.repositories.AuthResidenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthResidenceService {

    final
    AuthResidenceRepository authResidenceRepository;

    public AuthResidenceService(AuthResidenceRepository authResidenceRepository){
        this.authResidenceRepository = authResidenceRepository;
    }

    @Transactional
    public AuthResidenceModel save(AuthResidenceModel authResidenceModel){
        return authResidenceRepository.save(authResidenceModel);
    }

    public boolean existsByUserCPF(String userCPF) {
        return authResidenceRepository.existsByUserCPF(userCPF);
    }

    public boolean existsByUserName(String userName) {
        return authResidenceRepository.existsByUserName(userName);
    }

    public boolean existsByApartmentNumAndApartmentBlock(String apartmentNum, String apartmentBlock) {
        return authResidenceRepository.existsByApartmentNumAndApartmentBlock(apartmentNum, apartmentBlock);
    }

    public List<AuthResidenceModel> findAll() {
        return authResidenceRepository.findAll();
    }

    public Optional<AuthResidenceModel> findById(UUID id) {
        return authResidenceRepository.findById(id);
    }

    @Transactional
    public void delete(AuthResidenceModel authResidenceModel) {
        authResidenceRepository.delete(authResidenceModel);
    }
}
