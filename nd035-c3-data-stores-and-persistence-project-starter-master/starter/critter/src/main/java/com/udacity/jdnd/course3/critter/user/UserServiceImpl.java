package com.udacity.jdnd.course3.critter.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        return customerRepository.save(
                mapper.map(customerDTO, Customer.class));
    }
}
