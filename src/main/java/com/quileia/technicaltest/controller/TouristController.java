package com.quileia.technicaltest.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quileia.technicaltest.dto.tourist.TouristDTO;
import com.quileia.technicaltest.entity.Tourist;
import com.quileia.technicaltest.service.TouristService;
import com.quileia.technicaltest.utils.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tourists")
@CrossOrigin("*")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @GetMapping("/")
    public ResponseEntity<List<Tourist>> getTourists(){
        return new ResponseEntity(touristService.findAllTourists(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristDTO> getTourist(@PathVariable("id")String id){
        return new ResponseEntity(touristService.findTouristById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Tourist> saveTourist(@Valid @RequestBody Tourist tourist, BindingResult result){
        if(result.hasErrors()){
            String message = this.formatMessage(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return new ResponseEntity(touristService.saveTourist(tourist), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tourist> updateTourist(@Valid @PathVariable("id")String id, @RequestBody Tourist tourist, BindingResult result){
        return new ResponseEntity(touristService.updateTourist(id, tourist), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tourist> deleteTourist(@PathVariable("id")String id){
        return new ResponseEntity(touristService.deleteTourist(id), HttpStatus.OK);
    }

    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }

}
