package org.example.gymapi.service;

import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.dto.ActivityDTO;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.exception.GymNotFoundException;

import java.util.List;


public interface ActivityService {
    List<Activity> findAllActivities();

    Activity addActivity(ActivityDTO activityDTO) throws GymNotFoundException;
    Activity deleteActivity(long id) throws GymNotFoundException;
    Activity modifyActivity(long id, ActivityDTO activityDTO) throws GymNotFoundException ;
    Activity patchActivity(long id, String name) throws GymNotFoundException;

}