package com.BMS.BookMyShow.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/error")
    public String index1() {
        return "Greetings from Spring Boot!";
    }
}