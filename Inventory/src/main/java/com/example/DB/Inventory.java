package com.example.DB;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String skcode;
    private int quantity;


}
