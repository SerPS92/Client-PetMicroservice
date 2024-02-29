package com.example.ClientPetMicroservice.Controller;

import com.example.ClientPetMicroservice.Model.Pet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class PetController {

    private final RestTemplate restTemplate;

    String url = "http://localhost:8080";

    public PetController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/pets/{name}/{type}/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> addPets(@PathVariable(name = "name")String name,
                             @PathVariable(name = "type")String type,
                             @PathVariable(name = "age")int age){
        Pet pet = new Pet(name, type, age);
        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(url + "/pets", pet, Void.class);
            ResponseEntity<Pet[]> pets = restTemplate.getForEntity(url + "/pets", Pet[].class);
            HttpHeaders httpHeaders = pets.getHeaders();
            int total = Integer.parseInt(httpHeaders.get("total").get(0));

            if (total == 0){
                return null;
            }

            return Arrays.asList(pets.getBody());

        } catch (HttpClientErrorException e){
            e.printStackTrace();
            return null;
        }

    }

}
