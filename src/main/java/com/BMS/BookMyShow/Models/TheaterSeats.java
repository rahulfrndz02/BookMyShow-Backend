package com.BMS.BookMyShow.Models;

import com.BMS.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Slf4j
@Builder  //@Allargs is required with @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theater_seats")
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "seat_no", nullable = false)
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    //db connection, parent = Theater, child = theaterSeats
    @ManyToOne
    @JoinColumn
    private Theater theater;
}
