package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Entity
@Data //provide getter and setter also
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String movieName;

    private int duration;

    private Date releaseDate;

    //db connection, parent = movie, child = show
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> listOfShows;  //one movie can have multiple shows

}
