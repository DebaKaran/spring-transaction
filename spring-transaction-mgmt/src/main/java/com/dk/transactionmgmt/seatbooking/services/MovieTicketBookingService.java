package com.dk.transactionmgmt.seatbooking.services;

import com.dk.transactionmgmt.seatbooking.entities.Seat;
import com.dk.transactionmgmt.seatbooking.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieTicketBookingService {

    @Autowired
    private SeatRepository seatRepository;


    @Transactional
    public Seat bookSeat(Long seatId) {
        //fetch the existing seat by id
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id " + seatId));

        System.out.println(Thread.currentThread().getName() + " fetched seat with version " + seat.getVersion());

        if (seat.isBooked()) {
            throw new RuntimeException("Seat already booked !");
        }

        //booking seat
        seat.setBooked(true);
        //version check will occurs here
        return seatRepository.save(seat);
    }
}
