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
@Entity(name = "activity_client")
public class Activity_Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne()
    @JoinColumn(name = "activity_id")
    //@JsonBackReference(value = "garage-reparation")
    private Activity activity;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    //@JsonBackReference(value = "garage-reparation")
    private Client client;



}
