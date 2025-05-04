package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedules();
    List<Schedule> getPetSchedules(long petId);
    List<Schedule> getEmployeeSchedules(long employeeId);
    Schedule createSchedule(ScheduleDTO scheduleDTO);

}
