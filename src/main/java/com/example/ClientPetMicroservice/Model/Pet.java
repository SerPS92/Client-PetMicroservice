package com.example.ClientPetMicroservice.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Pet {

    private String name;
    private String type;
    private int age;
}
