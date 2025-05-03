package com.udacity.jdnd.course3.critter.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeIRepository employeeIRepository;
    private final CustomerMapper customerMapper;
    private final ModelMapper employeeMapper;

    public UserServiceImpl(CustomerRepository customerRepository, EmployeeIRepository employeeIRepository,
                           CustomerMapper customerMapper, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.employeeIRepository = employeeIRepository;
        this.customerMapper = customerMapper;
        this.employeeMapper = mapper;
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
    public Customer getCustomerByPet(long petId) {
        return customerRepository.findByPetsId(petId)
                .orElseThrow();
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        return customerRepository.save(
                customerMapper.convertToEntity(customerDTO));
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
                employeeMapper.map(employeeDTO, Employee.class));
    }
}
