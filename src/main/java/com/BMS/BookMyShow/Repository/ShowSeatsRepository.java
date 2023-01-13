package com.BMS.BookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BMS.BookMyShow.Models.ShowSeats;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeats, Integer> {
}
