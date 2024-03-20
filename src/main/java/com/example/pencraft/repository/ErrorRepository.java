package com.example.pencraft.repository;

import com.example.pencraft.domain.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {

    @Query(value = "SELECT * FROM error where error_id = :id", nativeQuery = true)
    Error findByProduct_errorCode(@RequestParam("id") Long id);
}
