package com.BMS.BookMyShow.Repository;

import com.BMS.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
