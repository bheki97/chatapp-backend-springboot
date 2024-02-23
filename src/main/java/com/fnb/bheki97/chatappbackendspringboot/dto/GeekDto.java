package com.fnb.bheki97.chatappbackendspringboot.dto;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class GeekDto {
    private Long geekId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String cellNumber;
    private Timestamp registrationDate;


    public GeekDto(Geek geek){
        this.geekId = geek.getGeekId();
        this.username = geek.getUsername();
        this.email = geek.getEmail();
        this.cellNumber = geek.getCellNumber();
        this.firstname  = geek.getFirstname();
        this.lastname  = geek.getLastname();
        this.registrationDate = geek.getRegistrationDate();
    }
}
