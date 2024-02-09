package com.BMS.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
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

    //db connection with ticket, parent = User, child = ticket
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //parent of ticket
    private List<Ticket> listOfTickets;
}
