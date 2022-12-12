package org.example.gymapi.repository;

import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Activity_Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityClientRepository extends CrudRepository<Activity_Client, Long> {


    List<Activity_Client> findAll();















}
