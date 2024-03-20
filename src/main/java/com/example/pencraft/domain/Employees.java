package com.example.pencraft.domain;

import com.example.pencraft.form.EmployeesForm;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "employees_seq",
        sequenceName = "employees_seq",
        initialValue = 10000, allocationSize = 1)
public class Employees {
    @Id
    @Column(name = "mainId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    private Long id;

    // 03.08 패치로 미사용
//    private String employees_id;
    private String password;
    private String name;

    private String role;


    public static Employees toEntity(EmployeesForm employeesForm) {
        return new Employees(
                employeesForm.getId(),
                employeesForm.getPassword(),
                employeesForm.getName(),
                employeesForm.getRole()
        );
    }

}
