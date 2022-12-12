package org.example.gymapi.service;


import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.exception.GymNotFoundException;

import java.util.List;


public interface ClientService {
    List<Client> findAllClients();

    Client addClient(ClientDTO clientDTO) throws GymNotFoundException;
    Client deleteClient(long id) throws GymNotFoundException;
    Client modifyClient(long id, ClientDTO clientDTO) throws GymNotFoundException ;
    Client patchClient(long id, String name) throws GymNotFoundException;

}