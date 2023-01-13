package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.RequestDto.TheaterRequestDto;
import com.BMS.BookMyShow.Service.TheaterService;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        theaterService.addTheater(theaterRequestDto);
        return new ResponseEntity<>("Theater added successfully", HttpStatus.CREATED);
    }
}
