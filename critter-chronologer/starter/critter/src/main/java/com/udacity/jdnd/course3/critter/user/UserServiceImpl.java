package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerMapper customerMapper;
    private final ModelMapper employeeMapper;

    public UserServiceImpl(CustomerRepository customerRepository, EmployeeRepository employeeRepository,
                           CustomerMapper customerMapper, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
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
                .orElseThrow(
                        () -> new EntityNotFoundException("Customer with id not found: " + customerId));
    }

    @Override
    public Customer getCustomerByPet(long petId) {
        return customerRepository.findByPetsId(petId)
                .orElseThrow();
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(
                customerMapper.convertToEntity(customerDTO));
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Employee with id not found: " + employeeId));
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.save(
                employeeMapper.map(employeeDTO, Employee.class));
    }

    @Override
    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Employee with id not found: " + employeeId));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesForService(EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> serviceSkills = employeeDTO.getSkills();
        DayOfWeek dayOfService = employeeDTO.getDate().getDayOfWeek();

        List<Employee> candidates = employeeRepository.findByDaysAvailable(dayOfService);

        return candidates.stream()
                .filter(e -> e.getSkills().containsAll(serviceSkills))
                .collect(Collectors.toList());
    }
}
