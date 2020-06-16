package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
