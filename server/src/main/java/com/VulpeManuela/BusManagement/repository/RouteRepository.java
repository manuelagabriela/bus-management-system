package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
