package com.udacity.jdnd.course3.critter.schedule;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ModelMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }
}
