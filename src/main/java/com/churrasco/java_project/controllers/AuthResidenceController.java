package com.churrasco.java_project.controllers;

import com.churrasco.java_project.dtos.AuthResidenceDto;
import com.churrasco.java_project.models.AuthResidenceModel;
import com.churrasco.java_project.services.AuthResidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/auth-residence")
public class AuthResidenceController{

    final AuthResidenceService authResidenceService;

    public AuthResidenceController(AuthResidenceService authResidenceService) {
        this.authResidenceService = authResidenceService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAuthResidence(@RequestBody @Valid AuthResidenceDto authResidenceDto){
        if(authResidenceService.existsByUserCPF(authResidenceDto.getUserCPF())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
        }
        if(authResidenceService.existsByUserName(authResidenceDto.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
        }
        if(authResidenceService.existsByApartmentNumAndApartmentBlock(authResidenceDto.getApartmentNum(), authResidenceDto.getApartmentBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartment Number and Apartment Block is already in use!");
        }
        var authResidenceModel = new AuthResidenceModel();
        BeanUtils.copyProperties(authResidenceDto, authResidenceModel);
        authResidenceModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(authResidenceService.save(authResidenceModel));
    }

    @GetMapping
    public ResponseEntity<List<AuthResidenceModel>> getAllAuthResidences(){
        return ResponseEntity.status(HttpStatus.OK).body(authResidenceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAuthResidence(@PathVariable(value = "id")UUID id){
        Optional<AuthResidenceModel> authResidenceModelOptional = authResidenceService.findById(id);
        if(!authResidenceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Morador não achado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(authResidenceModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthResidence(@PathVariable(value = "id") UUID id){
        Optional<AuthResidenceModel> authResidenceModelOptional = authResidenceService.findById(id);
        if(!authResidenceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Morador não achado.");
        }
        authResidenceService.delete(authResidenceModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Morador deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthResidence(@PathVariable(value = "id") UUID id, @RequestBody @Valid AuthResidenceDto authResidenceDto){
        Optional<AuthResidenceModel> authResidenceModelOptional = authResidenceService.findById(id);
        if(!authResidenceModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Morador não achado.");
        }
        var authResidenceModel = new AuthResidenceModel();
        BeanUtils.copyProperties(authResidenceDto, authResidenceModel);
        authResidenceModel.setId(authResidenceModelOptional.get().getId());
        authResidenceModel.setRegistrationDate(authResidenceModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(authResidenceService.save(authResidenceModel));
    }
}
