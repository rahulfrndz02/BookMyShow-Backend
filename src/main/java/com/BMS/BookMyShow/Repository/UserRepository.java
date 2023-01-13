package com.BMS.BookMyShow.Repository;

import com.BMS.BookMyShow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
