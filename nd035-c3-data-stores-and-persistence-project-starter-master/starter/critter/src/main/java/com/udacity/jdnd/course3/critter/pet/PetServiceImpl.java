package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    private final PetMapper mapper;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository, PetMapper mapper) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPets(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public Pet getPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow();
    }

    @Transactional
    @Override
    public Pet save(PetDTO petDTO) {
        Pet petToSave = mapper.convertToEntity(petDTO);

        Customer owner = customerRepository.findById(petDTO.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + petDTO.getOwnerId()));
        petToSave.setOwner(owner);

        Pet savedPet = petRepository.save(petToSave);
        owner.getPets().add(savedPet);

        return savedPet;
    }
}
