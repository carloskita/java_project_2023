package com.churrasco.java_project.repositories;

import com.churrasco.java_project.models.AuthResidenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthResidenceRepository extends JpaRepository<AuthResidenceModel, UUID> {
    public boolean existsByUserCPF(String userCPF);
    public boolean existsByUserName(String userName);
    public boolean existsByApartmentNumAndApartmentBlock(String apartmentNum, String apartmentBlock);
}
