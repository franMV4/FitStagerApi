package org.example.gymapi.controller;


import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.Staff;
import org.example.gymapi.domain.dto.GymDTO;
import org.example.gymapi.domain.dto.StaffDTO;
import org.example.gymapi.exception.ErrorResponse;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.service.ActivityService;
import org.example.gymapi.service.ClientService;
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
public class GymController {

    private final Logger logger = LoggerFactory.getLogger(GymController.class);

    @Autowired
    private GymService gymService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ActivityService activityService;


    @GetMapping("/gyms")
    public ResponseEntity<List<Gym>> getGyms() {
        List<Gym> gyms;

        logger.info("Start GetGyms ");

        gyms = gymService.findAllGyms();

        return ResponseEntity.ok(gyms);
    }

    @PostMapping("/gyms")
    public ResponseEntity<Gym> addGym(@Valid @RequestBody GymDTO gymDto) throws GymNotFoundException {
        Gym newGym = gymService.addGym(gymDto);
        logger.info("Start PostGym " );
        return ResponseEntity.ok(newGym);
    }


    @PutMapping("/gyms/{id}")
    public ResponseEntity<Gym> modifyGym(@PathVariable long id, @Valid @RequestBody GymDTO gymDTO)
            throws GymNotFoundException {
        logger.info("Inicio modifyGym");
        Gym gym = gymService.modifyGym(id, gymDTO);
        logger.info("Final modifyGym");
        return new ResponseEntity<>(gym, HttpStatus.OK);
    }



    @DeleteMapping("/gym/{id}")
    public ResponseEntity<Gym> removeGym(@PathVariable long id) throws GymNotFoundException {
        Gym gym = gymService.deleteGym(id);
        logger.info("Start DeleteGym " + id);
        return ResponseEntity.ok(gym);
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
