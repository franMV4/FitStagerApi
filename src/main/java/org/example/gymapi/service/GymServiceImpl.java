package org.example.gymapi.service;


import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.GymDTO;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.repository.ActivityRepository;
import org.example.gymapi.repository.ClientRepository;
import org.example.gymapi.repository.GymRepository;
import org.example.gymapi.repository.StaffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymServiceImpl implements GymService {


    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private StaffRepository staffRepository;


    @Override
    public List<Gym> findAllGyms() {

        return (List<Gym>) gymRepository.findAll();
    }

    @Override
    public Gym findGym(long id) throws GymNotFoundException {
        return null;
    }

    @Override
    public Gym addGym(GymDTO gymDto) throws GymNotFoundException {

        ModelMapper mapper = new ModelMapper();
        Gym gym = mapper.map(gymDto, Gym.class);

        return gymRepository.save(gym);}


    @Override
    public Gym deleteGym(long id) throws GymNotFoundException {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(GymNotFoundException::new);
        //car.setReparations(null);

        gymRepository.delete(gym);
        return gym;
    }

    @Override
    public Gym modifyGym(long id, GymDTO gymDTO) throws GymNotFoundException {
        gymRepository.findById(id).orElseThrow(GymNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Gym gym = mapper.map(gymDTO, Gym.class);

        gym.setId(id);


        gymRepository.save(gym);
        return gym;
    }

    @Override
    public Gym patchGym(long id, String name) throws GymNotFoundException {
        return null;
    }
}
