package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;
import java.util.Date;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String mobile;

    @CreationTimestamp //give date + time
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdOn;

    @UpdateTimestamp //give date + time
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;

    //db connection, parent = User, child = ticket
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //parent of ticket
    private List<Ticket> listOfTickets;
}
