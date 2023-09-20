package com.example.DB.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderLineItemsDTO {
    private long id;
    private String code;
    private double price;
    private int quantity;
}
