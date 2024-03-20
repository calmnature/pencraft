package com.example.pencraft.repository;

import com.example.pencraft.domain.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query(value = "SELECT * FROM lot where lot_id = :id", nativeQuery = true)
    Lot findByLot_id(@Param("id")Long id);

    @Query(value = "SELECT MAX(lot_id) FROM lot", nativeQuery = true)
    Long selectMaxLotId();

    @Query(value = "SELECT * FROM lot WHERE end_time >= :compare", nativeQuery = true)
    List<Lot> findByBeforeDays(@Param("compare") String compare);

    @Query(value = "SELECT * FROM lot WHERE end_time like :compare", nativeQuery = true)
    List<Lot> findByMonth(@Param("compare") String compare);
}

