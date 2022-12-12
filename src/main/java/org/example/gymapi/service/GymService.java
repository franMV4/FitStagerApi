package org.example.gymapi.service;

import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.dto.GymDTO;
import org.example.gymapi.exception.GymNotFoundException;

import java.util.List;


public interface GymService {
    List<Gym> findAllGyms();
    Gym findGym(long id) throws GymNotFoundException;





    Gym addGym(GymDTO gymDTO) throws GymNotFoundException;

    Gym deleteGym(long id) throws GymNotFoundException;
    Gym modifyGym(long id, GymDTO gymDTO) throws GymNotFoundException ;
    Gym patchGym(long id, String name) throws GymNotFoundException ;

}
