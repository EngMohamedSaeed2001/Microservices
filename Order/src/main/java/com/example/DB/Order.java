package com.example.DB;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)

    private List<OrderLineItems> orderLineItemsList;
}
