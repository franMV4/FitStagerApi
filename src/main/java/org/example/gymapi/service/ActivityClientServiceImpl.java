package org.example.gymapi.service;

import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Activity_Client;
import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.Activity_ClientDTO;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.repository.ActivityClientRepository;
import org.example.gymapi.repository.ActivityRepository;
import org.example.gymapi.repository.ClientRepository;
import org.example.gymapi.repository.GymRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityClientServiceImpl implements ActivityClientService{


    @Autowired
    private ActivityClientRepository activityclientRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ActivityRepository activityRepository;




    @Override
    public List<Activity_Client> findAllActivitiesClients() {
        return (List<Activity_Client>) activityclientRepository.findAll();
    }

    @Override
    public Activity_Client addActivityClient(Activity_ClientDTO activity_clientDTO) throws GymNotFoundException {


        ModelMapper mapper = new ModelMapper();
        Activity_Client activity_client = mapper.map(activity_clientDTO, Activity_Client.class);

        activity_client.setActivity(activityRepository.findById(activity_clientDTO.getActivity()).orElseThrow(GymNotFoundException::new));
        activity_client.setClient(clientRepository.findById(activity_clientDTO.getClient()).orElseThrow(GymNotFoundException::new));

        return activityclientRepository.save(activity_client);
    }

    @Override
    public Activity_Client deleteActivityClient(long id) throws GymNotFoundException {
        Activity_Client activity_client = activityclientRepository.findById(id)
                .orElseThrow(GymNotFoundException::new);
        activity_client.setActivity(null);
        activity_client.setClient(null);

        activityclientRepository.delete(activity_client);
        return activity_client;
    }

    @Override
    public Activity_Client modifyActivityClient(long id, Activity_ClientDTO activity_clientDTO) throws GymNotFoundException {
        activityclientRepository.findById(id).orElseThrow(GymNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Activity_Client activity_client = mapper.map(activity_clientDTO, Activity_Client.class);

        activity_client.setId(id);

        activity_client.setActivity(activityRepository.findById(activity_clientDTO.getActivity())
                .orElseThrow(GymNotFoundException::new));

        activity_client.setClient(clientRepository.findById(activity_clientDTO.getClient())
                .orElseThrow(GymNotFoundException::new));

        activityclientRepository.save(activity_client);
        return activity_client;
    }

    @Override
    public Activity_Client patchActivityClient(long id, String name) throws GymNotFoundException {
        return null;
    }
}
