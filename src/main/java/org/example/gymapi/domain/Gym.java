package org.example.gymapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gym")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    //@NotNull
    private String name;
    @Column
    //@NotNull
    private String city;
    @Column
    //@NotNull
    private String street;

    @Column
    //@NotNull
    private String horary;

    @Column
    private float latitude;
    //@Value("0")
    //@NotNull
    @Column
    private float longitude;
    //@Value("null")
    @Lob
    @Column
    private String gymImage;


    @JsonBackReference
    @OneToMany(mappedBy = "gym")
    private List<Client> gym_client;

    @JsonBackReference
    @OneToMany(mappedBy = "gym")
    private List<Activity> gym_activity;

    @JsonBackReference
    @OneToMany(mappedBy = "gym")
    private List<Staff> gym_staff;


}
