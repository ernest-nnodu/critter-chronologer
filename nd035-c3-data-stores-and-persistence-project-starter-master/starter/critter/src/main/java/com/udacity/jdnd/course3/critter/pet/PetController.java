package com.udacity.jdnd.course3.critter.pet;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final ModelMapper mapper;

    public PetController(PetService petService, ModelMapper mapper) {
        this.petService = petService;
        this.mapper = mapper;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return mapper.map(petService.save(petDTO), PetDTO.class);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return mapper.map(petService.getPet(petId), PetDTO.class);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getPets();

        return pets.stream()
                .map(pet -> mapper.map(pet, PetDTO.class))
                .toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> pets = petService.getPets(ownerId);

        return pets.stream()
                .map(pet -> mapper.map(pet, PetDTO.class))
                .toList();
    }
}
