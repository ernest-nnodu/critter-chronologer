package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository, ModelMapper mapper) {
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
        return List.of();
    }

    @Override
    public Pet getPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow();
    }

    @Override
    public Pet save(PetDTO petDTO) {

        Customer owner = customerRepository.findById(petDTO.getOwnerId())
                .orElseThrow();
        return petRepository.save(mapper.map(petDTO, Pet.class));
    }
}
