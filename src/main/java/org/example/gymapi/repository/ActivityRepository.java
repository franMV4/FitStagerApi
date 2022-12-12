package org.example.gymapi.repository;

import org.example.gymapi.domain.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {


    List<Activity> findAll();















}
