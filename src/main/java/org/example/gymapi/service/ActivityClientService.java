package org.example.gymapi.service;

import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Activity_Client;
import org.example.gymapi.domain.dto.ActivityDTO;
import org.example.gymapi.domain.dto.Activity_ClientDTO;
import org.example.gymapi.exception.GymNotFoundException;

import java.util.List;


public interface ActivityClientService {

    List<Activity_Client> findAllActivitiesClients();

    Activity_Client addActivityClient(Activity_ClientDTO activityDTO) throws GymNotFoundException;
    Activity_Client deleteActivityClient(long id) throws GymNotFoundException;
    Activity_Client modifyActivityClient(long id, Activity_ClientDTO activity_clientDTO) throws GymNotFoundException ;
    Activity_Client patchActivityClient(long id, String name) throws GymNotFoundException;

}
