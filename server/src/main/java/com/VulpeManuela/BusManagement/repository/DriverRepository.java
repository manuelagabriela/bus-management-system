package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
