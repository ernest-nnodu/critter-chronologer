package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeIRepository extends JpaRepository<Employee, Long> {
}
