package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Models.Show;
import com.BMS.BookMyShow.Models.ShowSeats;
import com.BMS.BookMyShow.Models.Ticket;
import com.BMS.BookMyShow.Models.User;
import com.BMS.BookMyShow.Repository.ShowRepository;
import com.BMS.BookMyShow.Repository.TicketRepository;
import com.BMS.BookMyShow.Repository.UserRepository;
import com.BMS.BookMyShow.RequestDto.BookTicketRequestDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception {
        // Get the requested seats
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();
        Show show = showRepository.findById(bookTicketRequestDto.getShowId())
                .orElseThrow(() -> new Exception("Show not found with ID: " + bookTicketRequestDto.getShowId()));
        User user = userRepository.findById(bookTicketRequestDto.getUserId())
                .orElseThrow(() -> new Exception("User not found with ID: " + bookTicketRequestDto.getUserId()));

        // Get the show seats from the show entity
        List<ShowSeats> showSeats = show.getListOfSeats();

        // Validate if seats can be allocated to the user
        List<ShowSeats> bookedSeats = new ArrayList<>();
        for (ShowSeats showSeat : showSeats) {
            String seatNo = showSeat.getSeatNo();
            if (!showSeat.isBooked() && requestedSeats.contains(seatNo)) {
                bookedSeats.add(showSeat);
            }
        }

        // Check if all requested seats are available
        if (bookedSeats.size() != requestedSeats.size()) {
            throw new Exception("Requested seats are not available");
        }

        // Calculate total amount for the tickets
        double totalAmount = 0;
        double multiplier = show.getMultiplier();
        for (ShowSeats bookedSeat : bookedSeats) {
            String seatNo = bookedSeat.getSeatNo();
            int rate = (seatNo.charAt(0) == '1') ? 100 : 200;
            totalAmount += multiplier * rate;
        }

        // Create a payment intent with Stripe
        String paymentIntent = createPaymentIntent((int) totalAmount);
        if (paymentIntent != null) {
            // Ticket booking successful, proceed to save ticket information
            Ticket ticket = new Ticket();
            ticket.setBooked_at(new Date());
            ticket.setAmount((int) totalAmount);
            ticket.setShow(show);
            ticket.setUser(user);
            ticket.setBookedSeats(bookedSeats);
            String allotedSeats = String.join(",", requestedSeats);
            ticket.setAlloted_seats(allotedSeats);

            // Save the ticket information
            ticketRepository.save(ticket);

            return "Ticket Booked Successfully";
        } else {
            // Error creating payment intent
            throw new Exception("Error creating payment intent");
        }
    }

    public String createPaymentIntent(Integer amount) {
        Stripe.apiKey = stripeApiKey;

        try {
            PaymentIntent intent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setCurrency("usd")
                            .setAmount((long) amount * 100)
                            .build()
            );
            return generateResponse(intent.getClientSecret());
        } catch (StripeException e) {
            // Handle StripeException
            e.printStackTrace();
            return null;
        }
    }

    private String generateResponse(String clientSecret) {
        // You can customize this method to generate the desired response.
        return "{\"clientSecret\":\"" + clientSecret + "\"}";
    }
}
