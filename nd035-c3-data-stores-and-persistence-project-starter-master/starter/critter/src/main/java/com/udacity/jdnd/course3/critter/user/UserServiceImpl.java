package com.udacity.jdnd.course3.critter.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeIRepository employeeIRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(CustomerRepository customerRepository, EmployeeIRepository employeeIRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.employeeIRepository = employeeIRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow();
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        return customerRepository.save(
                mapper.map(customerDTO, Customer.class));
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeIRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeIRepository.findById(employeeId)
                .orElseThrow();
    }

    @Override
    public Employee save(EmployeeDTO employeeDTO) {
        return employeeIRepository.save(
                mapper.map(employeeDTO, Employee.class));
    }
}
