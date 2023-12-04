package com.fnb.bheki97.chatappbackendspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "geeks")
public class Geek {


    @Id
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(name = "cellphone_number",nullable = false,unique = true)
    private String cellNumber;
    @Column(nullable = false,unique = true)
    private String password;
    @Column(name = "registration_date",nullable = false)
    private Timestamp registrationDate;

    public Geek() {
    }

    public Geek(String email, String firstname, String lastname, String cellNumber, String password, Timestamp registrationDate) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cellNumber = cellNumber;
        this.password = password;
        this.registrationDate = registrationDate;
    }


}
