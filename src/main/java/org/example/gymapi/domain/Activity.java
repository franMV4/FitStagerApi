package org.example.gymapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    //@NotNull
    private String name;
    @Column
    //@NotNull
    private String difficulty;
    @Column
    //@NotNull
    private String room;

    @Column
    //@NotNull
    private String style;

    //TODO Cambiar en la base de datos a datetime
 @Column
 @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @Lob
    @Column
    private String activityImage;




    @JsonBackReference
    @OneToMany(mappedBy = "activity")
    private List<Activity_Client> activity_activity_client;

    @ManyToOne()
    @JoinColumn(name = "staff_id")
    //@JsonBackReference(value = "garage-reparation")
    private Staff staff;


    @ManyToOne()
    @JoinColumn(name = "gym_id")
    //@JsonBackReference(value = "garage-reparation")
    private Gym gym;

}
