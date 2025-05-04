package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    private final ModelMapper mapper;

    public PetMapper(ModelMapper mapper, CustomerRepository customerRepository) {
        this.mapper = mapper;
        configureMappings();
    }

    private void configureMappings() {
        mapper.addMappings(new PropertyMap<Pet, PetDTO>() {
            @Override
            protected void configure() {
                map().setOwnerId(source.getOwner().getId());
            }
        });

        mapper.addMappings(new PropertyMap<PetDTO, Pet>() {
            @Override
            protected void configure() {
                skip(destination.getOwner());
            }
        });
    }

    public PetDTO convertToDTO(Pet pet) {
        return mapper.map(pet, PetDTO.class);
    }

    public Pet convertToEntity(PetDTO petDTO) {
        return mapper.map(petDTO, Pet.class);
    }
}
