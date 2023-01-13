package com.BMS.BookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BMS.BookMyShow.Models.TheaterSeats;

public interface TheaterSeatsRepository extends JpaRepository<TheaterSeats, Integer> {
}
