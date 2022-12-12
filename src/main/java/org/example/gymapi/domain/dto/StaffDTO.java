package org.example.gymapi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class StaffDTO {

    private String name;
    private String surname;
    private String dni;
    private String phone;
    private String staffImage;


    private long gym;

}
