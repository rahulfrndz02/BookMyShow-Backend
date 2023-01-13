package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.Service.ShowSeatsService;
import com.BMS.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("showSeats")
public class ShowSeatsController {

    @Autowired
    ShowSeatsService showSeatsService;
}
