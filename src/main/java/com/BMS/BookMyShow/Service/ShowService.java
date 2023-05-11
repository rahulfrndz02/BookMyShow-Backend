package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Models.*;
import com.BMS.BookMyShow.Repository.MovieRepository;
import com.BMS.BookMyShow.Repository.ShowRepository;
import com.BMS.BookMyShow.Repository.ShowSeatsRepository;
import com.BMS.BookMyShow.Repository.TheaterRepository;
import com.BMS.BookMyShow.RequestDto.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){

        //show entity
        Show show = Show.builder().
                showDate(showRequestDto.getShowDate()).
                showtime(showRequestDto.getShowTime()).
                multiplier(showRequestDto.getMultiplier()).build();

        //need to get movie repo
        Movie movie = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //need to get theater repo
        Theater theater = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        //bidirectional mapping
        show.setTheater(theater);
        show.setMovie(movie);
        //bcz we are doing a bidirectional mapping :
        //Optional things :
//        List<ShowEntity> currentListOfShow = movieEntity.getListOfShows();
//        currentListOfShow.add(showEntity);
//        movieEntity.setListOfShows(currentListOfShow);
        movie.getListOfShows().add(show);
        theater.getListOfShows().add(show);

        List<ShowSeats> seatsList = createShowSeats(theater.getTheaterSeatsList());

        show.setListOfSeats(seatsList);

        //for each ShowSeats : we need to mark that to which show is belongs(foreign key will be filled)
        for(ShowSeats showSeats : seatsList){
            showSeats.setShow(show);
        }
        movieRepository.save(movie);
        theaterRepository.save(theater);
        //showRepository.save(showEntity); this doesn't need to be called bcz parent save function is being called.

        return "Show added successfully";
    }
    public List<ShowSeats> createShowSeats(List<TheaterSeats> theaterSeatsList){

        List<ShowSeats> seats = new ArrayList<>();

        for(TheaterSeats theaterSeats : theaterSeatsList){
            ShowSeats showSeats = ShowSeats.builder().
                    seatNo(theaterSeats.getSeatNo()).
                    seatType(theaterSeats.getSeatType()).build();

            seats.add(showSeats);
        }
        showSeatsRepository.saveAll(seats);
        return seats;
    }
}
