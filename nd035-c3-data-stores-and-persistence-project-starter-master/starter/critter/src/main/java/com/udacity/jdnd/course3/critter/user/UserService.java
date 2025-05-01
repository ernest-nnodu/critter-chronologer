package com.udacity.jdnd.course3.critter.user;

import java.util.List;

public interface UserService {

    List<Customer> getCustomers();
    Customer getCustomer(Long customerId);
    Customer save(CustomerDTO customerDTO);
    List<Employee> getEmployees();
    Employee getEmployee(Long employeeId);
    Employee save(EmployeeDTO employeeDTO);
}
