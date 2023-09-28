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
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String userId;
//
//    private String userName;
//
//    private String email;
//
//    private String gender;
//
//    private String guardian;
//
//    @PrePersist
//    public void generateUserId() {
//        if (userId == null) {
//            userId = "u_" + (id + 100);
//        }
//    }
//}
