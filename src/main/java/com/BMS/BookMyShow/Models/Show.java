package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showtime;

    private double multiplier;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //db connection with movie , parent - Movie, child - show
    @ManyToOne
    @JoinColumn
    private Movie movie;  //multiple shows have a movie

    //db connection with ShowSeats, parent = Show, child = ShowSeats
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeats> listOfSeats;

    //db connection with Theater, parent = Theater, child = Show
    @ManyToOne
    @JoinColumn
    private Theater theater;

    //db connection with Ticket, sne shows having multiple tickets, Parent = Show, child = Ticket
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket> listOfTickets;

}
