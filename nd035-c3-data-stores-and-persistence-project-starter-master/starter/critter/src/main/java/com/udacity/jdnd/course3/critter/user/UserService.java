package com.udacity.jdnd.course3.critter.user;

import java.util.List;

public interface UserService {

    List<Customer> listCustomers();
    Customer save(CustomerDTO customerDTO);
}
