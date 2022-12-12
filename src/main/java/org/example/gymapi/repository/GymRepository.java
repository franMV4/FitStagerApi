package org.example.gymapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.example.gymapi.domain.Gym;

import java.util.List;

@Repository
public interface GymRepository extends CrudRepository<Gym, Long> {

    List<Gym> findAll();

}
