package org.example.gymapi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class ActivityDTO {

    private String name;
    private String difficulty;
    private String room;
    private String style;
    private LocalDate date;
    private String activityImage;



    private long staff;
    private long activity_client;
    private long gym;
}
