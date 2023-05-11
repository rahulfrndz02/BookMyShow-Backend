package com.BMS.BookMyShow.Convertor;

import com.BMS.BookMyShow.Models.User;
import com.BMS.BookMyShow.RequestDto.UserRequestDto;

//no annotations required
public class UserConverter {

    public static User convertUserDtoToEntity(UserRequestDto userRequestDto){
        User user = User.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
        return user;
    }
}
