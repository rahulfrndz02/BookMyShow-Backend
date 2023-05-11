package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Convertor.TheaterConverter;
import com.BMS.BookMyShow.Enums.SeatType;
import com.BMS.BookMyShow.Models.Theater;
import com.BMS.BookMyShow.Models.TheaterSeats;
import com.BMS.BookMyShow.Repository.TheaterRepository;
import com.BMS.BookMyShow.Repository.TheaterSeatsRepository;
import com.BMS.BookMyShow.RequestDto.TheaterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.BMS.BookMyShow.Enums.SeatType.*;

@Service
//@Slf4j //for log
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatsRepository theaterSeatsRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){

           Theater theater = TheaterConverter.convertTheaterDtoToEntity(theaterRequestDto); //converting dto to entity
           List<TheaterSeats> theaterSeatsList = createTheaterSeats(); //getting theater seat list
           theater.setTheaterSeatsList(theaterSeatsList); //from Theater entity, Bidirectional mapping

            //for each theater seat : we need to set theater entity
            for(TheaterSeats theaterSeat : theaterSeatsList){
                theaterSeat.setTheater(theater);
            }
            //saving into theaterRepo
            theaterRepository.save(theater);

        return "Theater added successfully";
    }

    private List<TheaterSeats> createTheaterSeats (){
           List<TheaterSeats> seats = new ArrayList<>();

        //optimise way for first 5 classic seats
        for(int i=0; i<5; i++) {
            char ch = (char)('A' + i);
            String seatNo = "1" + ch;
            TheaterSeats theaterSeats = new TheaterSeats(seatNo, SeatType.CLASSIC, 100);
            seats.add(theaterSeats);
        }

        //optimise way for last 5 platinum seats
        for(int i=0; i<5; i++) {
            char ch = (char)('A' + i);
            String seatNo = "2" + ch;
            TheaterSeats theaterSeats = new TheaterSeats(seatNo, PLATINUM, 200);
            seats.add(theaterSeats);
        }
//            TheaterSeats theaterSeats1 = new TheaterSeats("1A", SeatType.CLASSIC, 100);
//            TheaterSeats theaterSeats2 = new TheaterSeats("1B", SeatType.CLASSIC, 100);
//            TheaterSeats theaterSeats3 = new TheaterSeats("1C", SeatType.CLASSIC, 100);
//            TheaterSeats theaterSeats4 = new TheaterSeats("1D", SeatType.CLASSIC, 100);
//            TheaterSeats theaterSeats5 = new TheaterSeats("1E", SeatType.CLASSIC, 100);
//            TheaterSeats theaterSeats6 = new TheaterSeats("2A", SeatType.PLATINUM, 200);
//            TheaterSeats theaterSeats7 = new TheaterSeats("2B", SeatType.PLATINUM, 200);
//            TheaterSeats theaterSeats8 = new TheaterSeats("2C", SeatType.PLATINUM, 200);
//            TheaterSeats theaterSeats9 = new TheaterSeats("2D", SeatType.PLATINUM, 200);
//            TheaterSeats theaterSeats10 = new TheaterSeats("2E",SeatType.PLATINUM, 200);
//
//            seats.add(theaterSeats1);
//            seats.add(theaterSeats2);
//            seats.add(theaterSeats3);
//            seats.add(theaterSeats4);
//            seats.add(theaterSeats5);
//            seats.add(theaterSeats6);
//            seats.add(theaterSeats7);
//            seats.add(theaterSeats8);
//            seats.add(theaterSeats9);
//            seats.add(theaterSeats10);

        theaterSeatsRepository.saveAll(seats);
        return seats;
    }
}
