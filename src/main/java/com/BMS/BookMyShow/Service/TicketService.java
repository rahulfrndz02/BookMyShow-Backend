package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Models.Show;
import com.BMS.BookMyShow.Models.ShowSeats;
import com.BMS.BookMyShow.Models.Ticket;
import com.BMS.BookMyShow.Models.User;
import com.BMS.BookMyShow.Repository.ShowRepository;
import com.BMS.BookMyShow.Repository.TicketRepository;
import com.BMS.BookMyShow.Repository.UserRepository;
import com.BMS.BookMyShow.RequestDto.BookTicketRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
//@Slf4j //for log
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{
        //get the requested seats
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats(); //requestedSeats list
        Show show = showRepository.findById(bookTicketRequestDto.getShowId()).get(); //finding show by id
        User user = userRepository.findById(bookTicketRequestDto.getUserId()).get(); //finding user by id

        //get the showSeats from show entity
        List<ShowSeats> showSeats = show.getListOfSeats();

        //validate if I can allocate these seats to the requested by the user
        List<ShowSeats> bookedSeats = new ArrayList<>();
        for(ShowSeats showSeat : showSeats){
            String seatNo = showSeat.getSeatNo();

            if(!showSeat.isBooked() && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }
        //FAILURE
        if(bookedSeats.size() != requestedSeats.size()){ //if reqSeat is not avail
                //some the requestedSeats are not ava   il
            throw new Exception("Requested seats are not available");
        }
        //SUCCESS
        //this means the bookedSeats actually contains the booked seats.
        Ticket ticket = new Ticket(); //ticket object
        double totalAmount =0;
        double multiplier = show.getMultiplier();

        String allotedSeats = "";
        int rate = 0;

        //calculating amount, setting booked status, setting
        for(ShowSeats bookedSeat : bookedSeats) {
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticket);
            bookedSeat.setShow(show);

            String seatNo = bookedSeat.getSeatNo();

            allotedSeats = allotedSeats + seatNo + ",";
            if (seatNo.charAt(0) == '1')
                rate = 100;
             else
                rate = 200;

            totalAmount = totalAmount + multiplier * rate;

        }
            ticket.setBooked_at(new Date());
            ticket.setAmount((int)totalAmount);
            ticket.setShow(show);
            ticket.setUser(user);
            ticket.setBookedSeats(bookedSeats);
            ticket.setAlloted_seats(allotedSeats);

            //bidirectional part is pending

            ticketRepository.save(ticket);

            return "Ticket Booked Successfully";
    }
}
