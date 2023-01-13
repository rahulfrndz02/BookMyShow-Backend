package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;
    private LocalDateTime showTime;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //db connection with movie
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
