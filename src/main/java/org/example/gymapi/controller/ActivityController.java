package org.example.gymapi.controller;


import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.dto.ActivityDTO;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.exception.ErrorResponse;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.service.ActivityService;
import org.example.gymapi.service.ClientService;
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
public class ActivityController {

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);



    @Autowired
    private ClientService clientService;
    @Autowired
    private ActivityService activityService;




    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities() {
        List<Activity> activities;

        logger.info("Start GetActivities ");

        activities = activityService.findAllActivities();

        return ResponseEntity.ok(activities);
    }



    @PostMapping("/activities")
    public ResponseEntity<Activity> addActivity(@Valid @RequestBody ActivityDTO activityDTO) throws GymNotFoundException {
        Activity newActivity = activityService.addActivity(activityDTO);
        logger.info("Start PostActivity " );
        return ResponseEntity.ok(newActivity);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity<Activity> modifyActivity(@PathVariable long id, @Valid @RequestBody ActivityDTO activityDTO)
            throws GymNotFoundException {
        logger.info("Inicio modifyActivity");
        Activity activity = activityService.modifyActivity(id, activityDTO);
        logger.info("Final modifyActivity");
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }


    @DeleteMapping("/activity/{id}")
    public ResponseEntity<Activity> removeActivity(@PathVariable long id) throws GymNotFoundException {
        Activity activity = activityService.deleteActivity(id);
        logger.info("Start DeleteActivity " + id);
        return ResponseEntity.ok(activity);
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
