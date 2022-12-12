package org.example.gymapi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
public class ClientDTO {

    private String name;
    private String surname;
    private String dni;
    private String phone;
    private String clientImage;



    private long activity_client;
    private long gym;


}
