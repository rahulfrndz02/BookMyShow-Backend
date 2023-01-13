package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.Service.TheaterSeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theaterSeat")
public class TheaterSeatController {

    @Autowired
    TheaterSeatsService theaterSeatsService;
}

