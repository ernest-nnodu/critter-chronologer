package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapper {

    public CustomerDTO convertToDto(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());

        if (customer.getPets() != null) {
            customerDTO.setPetIds(
                    customer.getPets().stream()
                            .map(Pet::getId)
                            .toList());
        } else {
            customerDTO.setPetIds(new ArrayList<>()); // Ensure it's not null
        }

        return customerDTO;
    }

    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPets(new ArrayList<>());

        return customer;
    }
}
