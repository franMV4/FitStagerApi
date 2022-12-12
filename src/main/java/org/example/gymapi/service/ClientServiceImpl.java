package org.example.gymapi.service;


import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.repository.ClientRepository;
import org.example.gymapi.repository.GymRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private GymRepository gymRepository;


    @Override
    public List<Client> findAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client addClient(ClientDTO clientDTO) throws GymNotFoundException {


        ModelMapper mapper = new ModelMapper();
        Client client = mapper.map(clientDTO, Client.class);

        client.setGym(gymRepository.findById(clientDTO.getGym()).orElseThrow(GymNotFoundException::new));

        return clientRepository.save(client);
    }

    @Override
    public Client deleteClient(long id) throws GymNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(GymNotFoundException::new);
        client.setGym(null);


        clientRepository.delete(client);
        return client;
    }

    @Override
    public Client modifyClient(long id, ClientDTO clientDTO) throws GymNotFoundException {
        clientRepository.findById(id).orElseThrow(GymNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Client client = mapper.map(clientDTO, Client.class);

        client.setId(id);
        client.setGym(gymRepository.findById(clientDTO.getGym())
                .orElseThrow(GymNotFoundException::new));


        clientRepository.save(client);
        return client;
    }

    @Override
    public Client patchClient(long id, String name) throws GymNotFoundException {
        return null;
    }
}
