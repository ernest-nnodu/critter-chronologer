package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final ModelMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               EmployeeRepository employeeRepository,
                               PetRepository petRepository, ModelMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getPetSchedules(long petId) {
        return scheduleRepository.findAllByPets_Id(petId);
    }

    @Override
    public List<Schedule> getEmployeeSchedules(long employeeId) {
        return scheduleRepository.findAllByEmployees_Id(employeeId);
    }

    @Override
    public List<Schedule> getCustomerSchedules(long customerId) {
        return scheduleRepository.findAllByPets_Owner_Id(customerId);
    }

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) {

        Schedule scheduleToSave = scheduleMapper.map(scheduleDTO, Schedule.class);
        List<Employee> employees = scheduleDTO.getEmployeeIds().stream()
                .map(id -> employeeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Employee with id not found: " + id)))
                .toList();
        List<Pet> pets = scheduleDTO.getPetIds().stream()
                .map(id -> petRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Pet with id not found: " + id)))
                .toList();

        scheduleToSave.setEmployees(employees);
        scheduleToSave.setPets(pets);

        return scheduleRepository.save(scheduleToSave);
    }
}
