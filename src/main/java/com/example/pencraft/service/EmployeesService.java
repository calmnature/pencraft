package com.example.pencraft.service;

import com.example.pencraft.domain.Employees;
import com.example.pencraft.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository employeesRepository;

    public Long join(Employees employees) {

//        validateDuplicateEmployees(employees);
        employeesRepository.save(employees);

        return employees.getId();
    }

    public Long save(Employees employees) {
        employeesRepository.save(employees);
        return employees.getId();
    }

//    private void validateDuplicateEmployees(Employees employees) {
//        List<Employees> findEmployees = employeesRepository.findEmployees(employees.getId());
//        if (!findEmployees.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 사번입니다.");
//        }
//    }

    public List<Employees> findEmployees() {
        return employeesRepository.findAll();
    }


    public Employees updatePassword(Long id){
        Employees employees = employeesRepository.findByEmpId(id);
        log.info("DB에 있는 Entity = {}",employees);
        if(employees != null){
            employees.setPassword(employees.getId().toString());
            log.info("변경된 비밀번호 = {}",employees);
            return employeesRepository.save(employees);
        }
        return null;
    }

    public Boolean deleteEmployee(Long id){
        Employees target = employeesRepository.findByEmpId(id);
        if(target != null){
            employeesRepository.delete(target);
            return true;
        }
        return false;
    }



    public Long findMaxId() {
        log.info("가장 큰거 : " + employeesRepository.findMaxId());
        return employeesRepository.findMaxId();
    }


    public Employees modifyPassword(Employees employees, String password) {
        employees.setPassword(password);
        log.info("비밀번호 변경된 Employees = {}", employees);
        return employeesRepository.save(employees);
    }
}
