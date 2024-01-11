package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "geeks",indexes = {@Index(name = "idx_geeks_geek_id", columnList = "username")})
public class Geek {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "geek_id",nullable = false,unique = true)
    private Long geekId;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(name = "cellphone_number",nullable = false)
    private String cellNumber;
    @Column(nullable = false,unique = true)
    private String password;
    @Column(name = "registration_date",nullable = false)
    private Timestamp registrationDate;

    public Geek() {
    }

    public Geek(String username) {
        this.username = username;
    }

    public Geek(String username, String email, String firstname, String lastname, String cellNumber, String password, Timestamp registrationDate) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cellNumber = cellNumber;
        this.password = password;
        this.registrationDate = registrationDate;
    }


}
