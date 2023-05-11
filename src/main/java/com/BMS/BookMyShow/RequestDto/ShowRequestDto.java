package com.BMS.BookMyShow.RequestDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {

    LocalDate showDate;

    LocalTime showTime;

    String movieName;

    double multiplier;

    int theaterId;
}
