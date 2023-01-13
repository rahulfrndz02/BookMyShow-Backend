package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Entity
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alloted_seats;

    private int amount;

    private Date booked_at;


    //db connection with Ticket-User
    @ManyToOne
    @JoinColumn
    private User user;

    //db connection with Ticket-Show
    @ManyToOne
    @JoinColumn
    private Show show;

    //db connection with Ticket-ShowSeats
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<ShowSeats> bookedSeats;

}
