package com.BMS.BookMyShow.Repository;

import com.BMS.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
