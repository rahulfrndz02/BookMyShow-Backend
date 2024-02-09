package com.BMS.BookMyShow.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data //provide getter and setter also
@Builder // produces complex builder APIs for our classes.
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

    private String releaseDate;

    //db connection, parent = movie, child = show
    @JsonIgnore  //use this or create mvoiedto to get all movie list as entity
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> listOfShows;  //one movie can have multiple shows

}
