package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Convertor.MovieConverter;
import com.BMS.BookMyShow.Convertor.TheaterConverter;
import com.BMS.BookMyShow.Models.Movie;
import com.BMS.BookMyShow.Models.Theater;
import com.BMS.BookMyShow.Repository.MovieRepository;
import com.BMS.BookMyShow.Repository.TheaterRepository;
import com.BMS.BookMyShow.RequestDto.MovieRequestDto;
import com.BMS.BookMyShow.RequestDto.TheaterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j //for log
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto){
        try{
           Theater theater = TheaterConverter.convertTheaterDtoToEntity(theaterRequestDto);
            theaterRepository.save(theater);
        }catch (Exception e){
            log.info("createTheater has caused an error");
            return "Create Theater didn't work";
        }
        return "Theater added successfully";
    }
}
