package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.RequestDto.MovieRequestDto;
import com.BMS.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    //adding movie
    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto){
            movieService.addMovie(movieRequestDto);
            return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
        }
    }
