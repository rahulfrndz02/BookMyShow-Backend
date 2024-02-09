package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.RequestDto.BookTicketRequestDto;
import com.BMS.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public String bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) {
        try {
            return ticketService.bookTicket(bookTicketRequestDto);
        } catch (Exception e) {
            //System.out.println(e);
            return "Requested seats are not available";
        }
    }

//    @PostMapping("/book")
//    public ResponseEntity<String> bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) {
//        try {
//            // Charge the user using Stripe
//            stripeService.chargeCreditCard(bookTicketRequestDto.getUserId(), bookTicketRequestDto.getAmount());
//
//            // Book the ticket if payment is successful
//            String result = ticketService.bookTicket(bookTicketRequestDto);
//            return ResponseEntity.ok(result);
//        } catch (StripeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
//        } catch (SeatNotAvailableException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
//        }
//    }
}
