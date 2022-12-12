package org.example.gymapi.controller;


import org.example.gymapi.domain.Client;
import org.example.gymapi.domain.Gym;
import org.example.gymapi.domain.dto.ClientDTO;
import org.example.gymapi.domain.dto.GymDTO;
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
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);



    @Autowired
    private ClientService clientService;




    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients;

        logger.info("Start GetClients ");

        clients = clientService.findAllClients();

        return ResponseEntity.ok(clients);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody ClientDTO clientDTO) throws GymNotFoundException {
        Client newClient = clientService.addClient(clientDTO);
        logger.info("Start PostClient " );
        return ResponseEntity.ok(newClient);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> modifyClient(@PathVariable long id, @Valid @RequestBody ClientDTO clientDTO)
            throws GymNotFoundException {
        logger.info("Inicio modifyGym");
        Client client = clientService.modifyClient(id, clientDTO);
        logger.info("Final modifyGym");
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Client> removeClient(@PathVariable long id) throws GymNotFoundException {
        Client client = clientService.deleteClient(id);
        logger.info("Start DeleteClient " + id);
        return ResponseEntity.ok(client);
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
