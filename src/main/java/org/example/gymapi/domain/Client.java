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
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    // @NotNull
    private String name;
    @Column
    // @NotNull
    private String surname;
    @Column
    private String dni;
    @Column
    private String phone;
    @Lob
    @Column
    private String clientImage;


    @JsonBackReference
    @OneToMany(mappedBy = "client")
    private List<Activity_Client> client_activity_client;

    @ManyToOne()
    @JoinColumn(name = "gym_id")
    //@JsonBackReference(value = "garage-reparation")
    private Gym gym;




}
