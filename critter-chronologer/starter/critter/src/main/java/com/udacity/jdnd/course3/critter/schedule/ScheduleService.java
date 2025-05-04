package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedules();
    List<Schedule> getPetSchedules(long petId);
    Schedule createSchedule(ScheduleDTO scheduleDTO);
}
