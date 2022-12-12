package org.example.gymapi.controller;


import org.example.gymapi.domain.Activity;
import org.example.gymapi.domain.Activity_Client;
import org.example.gymapi.domain.dto.ActivityDTO;
import org.example.gymapi.domain.dto.Activity_ClientDTO;
import org.example.gymapi.exception.ErrorResponse;
import org.example.gymapi.exception.GymNotFoundException;
import org.example.gymapi.service.ActivityClientService;
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
public class ActivityClientController {

    private final Logger logger = LoggerFactory.getLogger(ActivityClientController.class);



    @Autowired
    private ClientService clientService;
    @Autowired
    private ActivityClientService activityclientService;




    @GetMapping("/activitiesclients")
    public ResponseEntity<List<Activity_Client>> getActivitiesClients() {
        List<Activity_Client> activitiesclients;

        logger.info("Start GetActivities ");

        activitiesclients = activityclientService.findAllActivitiesClients();

        return ResponseEntity.ok(activitiesclients);
    }



    @PostMapping("/activitiesclients")
    public ResponseEntity<Activity_Client> addActivityClient(@Valid @RequestBody Activity_ClientDTO activityclientDTO) throws GymNotFoundException {
        Activity_Client newActivityClient = activityclientService.addActivityClient(activityclientDTO);
        logger.info("Start PostActivity " );
        return ResponseEntity.ok(newActivityClient);
    }


    @PutMapping("/activityclient/{id}")
    public ResponseEntity<Activity_Client> modifyActivity_Client(@PathVariable long id, @Valid @RequestBody Activity_ClientDTO activity_clientDTO)
            throws GymNotFoundException {
        logger.info("Inicio modifyActivity");
        Activity_Client activity_client = activityclientService.modifyActivityClient(id, activity_clientDTO);
        logger.info("Final modifyActivity");
        return new ResponseEntity<>(activity_client, HttpStatus.OK);
    }


    @DeleteMapping("/activityclient/{id}")
    public ResponseEntity<Activity_Client> removeActivityClient(@PathVariable long id) throws GymNotFoundException {
        Activity_Client activityclient = activityclientService.deleteActivityClient(id);
        logger.info("Start DeleteActivityClient " + id);
        return ResponseEntity.ok(activityclient);
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
