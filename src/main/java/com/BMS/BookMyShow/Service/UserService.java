package com.BMS.BookMyShow.Service;

import com.BMS.BookMyShow.Convertor.UserConverter;
import com.BMS.BookMyShow.Models.User;
import com.BMS.BookMyShow.Repository.UserRepository;
import com.BMS.BookMyShow.RequestDto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j //for log
public class UserService {

    @Autowired
    UserRepository userRepository;

   public String createUser(UserRequestDto userRequestDto){
       User user = UserConverter.convertUserDtoToEntity(userRequestDto);
       userRepository.save(user);
       return "User Added Successfully";
   }
}
