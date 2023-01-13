package com.BMS.BookMyShow.Models;

import com.BMS.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@Table(name = "show_seats")
public class ShowSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean booked;

    private Date bookedAt; //when the ticket is booked not automatically, no annotation

    //db connection with show, Parent = Ticket, Child = ShowSeats
    @ManyToOne
    @JoinColumn
    private Ticket ticket;

    //db connection with show, Parent = Show, Child = ShowSeats
    @ManyToOne
    @JoinColumn
    private Show show;
}
