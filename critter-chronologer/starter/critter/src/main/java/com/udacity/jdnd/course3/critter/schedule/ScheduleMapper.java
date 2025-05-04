package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ScheduleMapper {

    private final ModelMapper mapper;

    public ScheduleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ScheduleDTO convertToDto(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(User::getId)
                .toList());

        scheduleDTO.setPetIds(schedule.getPets().stream()
                .map(Pet::getId)
                .toList());

        return scheduleDTO;
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setEmployees(new ArrayList<>());
        schedule.setPets(new ArrayList<>());

        return schedule;
    }
}
