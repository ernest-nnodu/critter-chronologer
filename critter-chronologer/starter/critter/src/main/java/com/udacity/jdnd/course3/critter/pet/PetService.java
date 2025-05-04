package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {

    List<Pet> getPets();
    List<Pet> getPets(Long ownerId);
    Pet getPet(Long petId);
    Pet createPet(PetDTO petDTO);

}
