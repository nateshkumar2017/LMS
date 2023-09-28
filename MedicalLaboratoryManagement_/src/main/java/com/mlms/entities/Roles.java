//package com.mlms.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Roles {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String rolesId;
//
//    private String roles;
//
//    @PrePersist
//    public void generateRoleId() {
//        if (rolesId == null) {
//            rolesId = "r_" + (id + 100);
//        }
//    }
//
//
//}
