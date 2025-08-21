package com.dk.transactionmgmt.seatbooking.repositories;

import com.dk.transactionmgmt.seatbooking.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
