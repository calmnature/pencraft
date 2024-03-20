package com.example.pencraft.service;

import com.example.pencraft.domain.Employees;
import com.example.pencraft.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final EmployeesRepository employeesRepository;

    public Employees login(String employees_id, String password) {
        Employees employees = employeesRepository.findById(Long.parseLong(employees_id)).orElse(null);
        if(employees != null){
            if(employees.getPassword().equals(password)){
                return employees;
            }
        }
        return null;
//        Optional<Employees> findEmployeesOptional = employeesRepository.findById(Long.parseLong(employees_id));
//        if (findEmployeesOptional.isEmpty()) {
//            return null;
//        }
//        Employees employees = findEmployeesOptional.get();
//
//        if (employees.getPassword().equals(password)) {
//            return employees;
//        }
//
//        return null;
    }
}
