package org.example.gymapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.example.gymapi.domain.Staff;


import java.util.List;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {


    List<Staff> findAll();















}
