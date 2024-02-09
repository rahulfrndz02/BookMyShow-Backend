package com.BMS.BookMyShow.RequestDto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {

    private String MovieName;
    private int duration;
    private String ReleaseDate;

}
