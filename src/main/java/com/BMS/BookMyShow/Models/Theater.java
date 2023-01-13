package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;


    //db connection, parent = Theater, child = Show
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Show> listOfShows;

    //db connection, parent = Theater, child = TheaterSeats
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeats> theaterSeatsList;

}
