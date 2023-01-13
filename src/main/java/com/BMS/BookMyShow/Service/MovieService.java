package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Models.Movie;
import com.BMS.BookMyShow.Repository.MovieRepository;
import com.BMS.BookMyShow.RequestDto.MovieRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j //for log
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto){
        try{
        Movie movie = MovieConvertor.convertMovieDtoToEntity(movieRequestDto);
        movieRepository.save(movie);
    }catch (Exception e){
        log.info("createAuthor has caused an error");
        return "Create author didn't work";
    }
        return "Author created successfully";
    }
}
