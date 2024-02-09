package com.BMS.BookMyShow.Models;

import com.BMS.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "theater_seats")
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column(columnDefinition = "seat_no", nullable = false) //if this will be used the db table will not be created
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    //db connection with theater, parent = Theater, child = theaterSeats
    @ManyToOne
    @JoinColumn
    private Theater theater; //this is the parent

    //all args constructor
    public TheaterSeats(String seatNo,SeatType seatType,int rate){
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }

}
