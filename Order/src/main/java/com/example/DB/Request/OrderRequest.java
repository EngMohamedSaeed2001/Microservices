package com.example.DB.Request;

import com.example.DB.OrderLineItems;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderRequest {
    private List<OrderLineItemsDTO> orderLineItemsDTOS;
}
