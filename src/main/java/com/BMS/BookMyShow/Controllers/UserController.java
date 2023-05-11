package com.BMS.BookMyShow.Controllers;

import com.BMS.BookMyShow.RequestDto.UserRequestDto;
import com.BMS.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }
}
