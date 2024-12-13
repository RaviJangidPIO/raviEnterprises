package com.remind.Quicker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private String imageOriginalName;
    private String type;
    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;
    private String role;
    private String status;
    private String orderStatus;
    @Column(nullable = false)
    private String deleteStatus;

    public CustomUser() {
    }

    public CustomUser(Long id, String name, String email, String mobileNumber, String password, String imageOriginalName, String type, String profileImage, String role, String status, String orderStatus, String deleteStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.imageOriginalName = imageOriginalName;
        this.type = type;
        this.profileImage = profileImage;
        this.role = role;
        this.status = status;
        this.orderStatus = orderStatus;
        this.deleteStatus = deleteStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageOriginalName() {
        return imageOriginalName;
    }

    public void setImageOriginalName(String imageOriginalName) {
        this.imageOriginalName = imageOriginalName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
