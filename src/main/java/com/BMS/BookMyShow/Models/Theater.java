package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
//import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;


    //db connection with show, parent = Theater, child = Show
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Show> listOfShows;

    //db connection with theater-seats, parent = Theater, child = TheaterSeats
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeats> theaterSeatsList; //this will be used in TheaterService layer

}
