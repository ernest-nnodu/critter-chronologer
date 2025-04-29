package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;

@Getter
@Setter
public class Employee extends User {

    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
}
