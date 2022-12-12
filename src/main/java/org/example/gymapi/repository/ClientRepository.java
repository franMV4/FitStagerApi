package org.example.gymapi.repository;

import org.example.gymapi.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {


    List<Client> findAll();















}
