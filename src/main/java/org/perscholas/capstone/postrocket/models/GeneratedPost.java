package org.perscholas.capstone.postrocket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class GeneratedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String post;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
