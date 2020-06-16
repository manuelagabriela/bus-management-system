package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}