package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.Models.Movie;
import com.BMS.BookMyShow.RequestDto.MovieRequestDto;
import com.BMS.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    //adding movie
    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto){
            movieService.addMovie(movieRequestDto);
            return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    //localhost:8066/movie/get_all_movie
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get_all_movie")
    public ResponseEntity<List<Movie>> getAllMovie(){
        List<Movie> allMovie = movieService.getAllMovie();
        return new ResponseEntity<>(allMovie, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Hello, I am admin");
    }
}
