package com.example.pencraft.repository;

import com.example.pencraft.domain.Employees;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    @Query(value = "SELECT MAX(main_id) FROM employees", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT * FROM employees WHERE main_id = :id", nativeQuery = true)
    Employees findByEmpId(@Param("id") Long id);
}
