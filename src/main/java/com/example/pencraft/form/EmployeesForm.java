package com.example.pencraft.form;

import com.example.pencraft.domain.Employees;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesForm {

    private Long id;
    private String password;
    private String name;
    private String role;

    public static EmployeesForm toDto(Employees employees) {
        EmployeesForm employeesForm = new EmployeesForm();
        employeesForm.setId(employees.getId());
        employeesForm.setName(employees.getName());
        employeesForm.setPassword(employees.getPassword());
        employeesForm.setRole(employees.getRole());
        return employeesForm;
    }

}
