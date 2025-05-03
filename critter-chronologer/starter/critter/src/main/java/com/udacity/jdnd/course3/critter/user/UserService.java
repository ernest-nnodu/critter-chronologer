package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface UserService {

    List<Customer> getCustomers();
    Customer getCustomer(Long customerId);
    Customer getCustomerByPet(long petId);
    Customer save(CustomerDTO customerDTO);
    List<Employee> getEmployees();
    Employee getEmployee(Long employeeId);
    Employee save(EmployeeDTO employeeDTO);
    void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);
    List<Employee> getEmployeesForService(EmployeeRequestDTO employeeDTO);
}
