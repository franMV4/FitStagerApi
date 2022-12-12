package org.example.gymapi.service;

import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.domain.dto.StaffDTO;
import org.example.gymapi.exception.GymNotFoundException;

import java.util.List;


public interface StaffService {
    List<Staff> findAllStaffs();

    Staff addStaff(StaffDTO staffDTO) throws GymNotFoundException;
    Staff deleteStaff(long id) throws GymNotFoundException;
    Staff modifyStaff(long id, StaffDTO staffDTO) throws GymNotFoundException ;
    Staff patchStaff(long id, String name) throws GymNotFoundException;

}