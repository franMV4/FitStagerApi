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
@Entity(name = "staff")
public class Staff {

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
    private String staffImage;


    @JsonBackReference
    @OneToMany(mappedBy = "staff")
    private List<Activity> staff_activity;

    @ManyToOne()
    @JoinColumn(name = "gym_id")
    //@JsonBackReference(value = "garage-reparation")
    private Gym gym;

}
