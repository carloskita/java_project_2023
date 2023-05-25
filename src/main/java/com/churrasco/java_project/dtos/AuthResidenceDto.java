package com.churrasco.java_project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthResidenceDto {

    @NotBlank
    @Size(max = 11)
    private String userCPF;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String userName;
    @NotBlank
    private String apartmentNum;
    @NotBlank
    private String apartmentBlock;

    public String getUserCPF() {
        return userCPF;
    }

    public void setUserCPF(String userCPF) {
        this.userCPF = userCPF;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(String apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getApartmentBlock() {
        return apartmentBlock;
    }

    public void setApartmentBlock(String apartmentBlock) {
        this.apartmentBlock = apartmentBlock;
    }
}
