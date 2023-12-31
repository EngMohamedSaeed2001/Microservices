package com.example.Service;

import com.example.DB.Order;
import com.example.DB.OrderLineItems;
import com.example.DB.Request.OrderLineItemsDTO;
import com.example.DB.Request.OrderRequest;
import com.example.DB.Response.InventoryResponse;
import com.example.Repository.OrderRepo;
import com.example.event.OrderPlacedEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor

@Transactional
public class OrderService {


   private final OrderRepo orderRepo;
   private final WebClient.Builder webClientBuilder;
   private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public String createOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTOS().stream().map(this::mapTo).toList();

        order.setOrderLineItemsList(orderLineItemsList);

        List<String>listOfCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getCode).toList();

       InventoryResponse[] result =  webClientBuilder.build().get()
               .uri("http://inventory-service/inventory",uriBuilder -> uriBuilder.queryParam("code",listOfCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
               .block();

       boolean allExist = Arrays.stream(result).allMatch(InventoryResponse::getExist);

       if(allExist) {
           orderRepo.save(order);
           kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
           return "Created";
       }
       else
           throw new IllegalArgumentException("Product is not in stock");
    }

    private OrderLineItems mapTo(OrderLineItemsDTO orderLineItemsDTO){

        return OrderLineItems.builder()
                .code(orderLineItemsDTO.getCode())
                .price(orderLineItemsDTO.getPrice())
                .quantity(orderLineItemsDTO.getQuantity())
                .build();
    }
}
