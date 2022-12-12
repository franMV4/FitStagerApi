package org.example.gymapi.controller;


import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.GymDTO;
import org.example.gymapi.domain.dto.StaffDTO;
import org.example.gymapi.exception.ErrorResponse;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.service.GymService;
import org.example.gymapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StaffController {

    private final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @Autowired
    private GymService gymService;

    @GetMapping("/staffs")
    public ResponseEntity<List<Staff>> getStaffs() {
        List<Staff> staffs;

        logger.info("Start GetStaffs ");

        staffs = staffService.findAllStaffs();

        return ResponseEntity.ok(staffs);
    }

    @PostMapping("/staffs")
    public ResponseEntity<Staff> addStaff(@Valid @RequestBody StaffDTO staffDto) throws GymNotFoundException {
        Staff newStaff = staffService.addStaff(staffDto);
        logger.info("Start PostStaff " );
        return ResponseEntity.ok(newStaff);
    }



    @PutMapping("/staff/{id}")
    public ResponseEntity<Staff> modifyStaff(@PathVariable long id, @Valid @RequestBody StaffDTO staffDTO)
            throws GymNotFoundException {
        logger.info("Inicio modifyStaff");
        Staff staff = staffService.modifyStaff(id, staffDTO);
        logger.info("Final modifyStaff");
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }




    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Staff> removeStaff(@PathVariable long id) throws GymNotFoundException {
        Staff staff = staffService.deleteStaff(id);
        logger.info("Start DeleteStaff " + id);
        return ResponseEntity.ok(staff);
    }



    @ExceptionHandler(GymNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGymNotFoundException(GymNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(404, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // TODO Más tipos de excepciones que puedan generar errores

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.generalError(999, "Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }





































}
