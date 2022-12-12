package org.example.gymapi.service;


import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.ActivityDTO;
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
public class ActivityServiceImpl implements ActivityService {



    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private GymRepository gymRepository;


    @Override
    public List<Activity> findAllActivities() {

        return (List<Activity>) activityRepository.findAll();
    }

    @Override
    public Activity addActivity(ActivityDTO activityDTO) throws GymNotFoundException {

        ModelMapper mapper = new ModelMapper();
        Activity activity = mapper.map(activityDTO, Activity.class);

        activity.setGym(gymRepository.findById(activityDTO.getGym()).orElseThrow(GymNotFoundException::new));
        activity.setStaff(staffRepository.findById(activityDTO.getStaff()).orElseThrow(GymNotFoundException::new));

        return activityRepository.save(activity);
    }

    @Override
    public Activity deleteActivity(long id) throws GymNotFoundException {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(GymNotFoundException::new);
        activity.setGym(null);
        activity.setStaff(null);

        activityRepository.delete(activity);
        return activity;
    }

    @Override
    public Activity modifyActivity(long id, ActivityDTO activityDTO) throws GymNotFoundException {

        activityRepository.findById(id).orElseThrow(GymNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Activity activity = mapper.map(activityDTO, Activity.class);

        activity.setId(id);

        activity.setGym(gymRepository.findById(activityDTO.getGym())
                .orElseThrow(GymNotFoundException::new));
        activity.setStaff(staffRepository.findById(activityDTO.getStaff())
                .orElseThrow(GymNotFoundException::new));


        activityRepository.save(activity);
        return activity;
    }

    @Override
    public Activity patchActivity(long id, String name) throws GymNotFoundException {
        return null;
    }
}
