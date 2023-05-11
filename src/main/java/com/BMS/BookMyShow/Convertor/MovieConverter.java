package com.BMS.BookMyShow.Convertor;

import com.BMS.BookMyShow.Models.Movie;
import com.BMS.BookMyShow.RequestDto.MovieRequestDto;

public class MovieConverter {
    public static Movie convertMovieDtoToEntity(MovieRequestDto movieRequestDto){
        Movie movie = Movie.builder().movieName(movieRequestDto.getMovieName()).
                duration(movieRequestDto.getDuration()).
                releaseDate(movieRequestDto.getReleaseDate()).build();
        return movie;
    }
}
