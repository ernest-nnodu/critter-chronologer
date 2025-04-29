package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer extends User{

    private String phoneNumber;
    private String notes;
    private List<Long> petIds;
}
