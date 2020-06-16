package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
