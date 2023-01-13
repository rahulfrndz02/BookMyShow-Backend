package com.BMS.BookMyShow.Convertor;

import com.BMS.BookMyShow.Models.Theater;
import com.BMS.BookMyShow.RequestDto.TheaterRequestDto;

public class TheaterConverter {

    public static Theater convertTheaterDtoToEntity(TheaterRequestDto theaterRequestDto){
        Theater theater = Theater.builder().name(theaterRequestDto.getName()).
                                            city(theaterRequestDto.getCity()).
                                            address(theaterRequestDto.getAddress()).build();
        return theater;

    }
}
