package com.BMS.BookMyShow.Repository;

import com.BMS.BookMyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByNameAndCity(String name, String city);
}
