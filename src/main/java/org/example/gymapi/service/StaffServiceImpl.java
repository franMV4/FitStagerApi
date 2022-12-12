package org.example.gymapi.service;


import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.StaffDTO;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.repository.ActivityRepository;
import org.example.gymapi.repository.ClientRepository;
import org.example.gymapi.repository.GymRepository;
import org.example.gymapi.repository.StaffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{


    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private GymRepository gymRepository;


    @Override
    public List<Staff> findAllStaffs() {
        return (List<Staff>) staffRepository.findAll();
    }

    @Override
    public Staff addStaff(StaffDTO staffDTO) throws GymNotFoundException {


        ModelMapper mapper = new ModelMapper();
        Staff staff = mapper.map(staffDTO, Staff.class);

        staff.setGym(gymRepository.findById(staffDTO.getGym()).orElseThrow(GymNotFoundException::new));

        return staffRepository.save(staff);
    }


    @Override
    public Staff deleteStaff(long id) throws GymNotFoundException {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(GymNotFoundException::new);
        staff.setGym(null);

        staffRepository.delete(staff);
        return staff;
    }

    @Override
    public Staff modifyStaff(long id, StaffDTO staffDTO) throws GymNotFoundException {
        staffRepository.findById(id).orElseThrow(GymNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Staff staff = mapper.map(staffDTO, Staff.class);

        staff.setId(id);
        staff.setGym(gymRepository.findById(staffDTO.getGym())
                .orElseThrow(GymNotFoundException::new));

        staffRepository.save(staff);
        return staff;
    }

    @Override
    public Staff patchStaff(long id, String name) throws GymNotFoundException {
        return null;
    }
}
