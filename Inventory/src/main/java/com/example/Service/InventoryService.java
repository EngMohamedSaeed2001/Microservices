package com.example.Service;

import com.example.DB.Inventory;
import com.example.DB.Request.InventoryRequest;
import com.example.DB.Response.InventoryResponse;
import com.example.Repository.InventoryRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> check(List<String> code){
        log.info("wait start");
        Thread.sleep(10000);
        log.info("wait end");
        return inventoryRepo.findBySkcodeIn(code).stream()
                .map(inventory -> InventoryResponse.builder()
                        .code(inventory.getSkcode())
                        .exist(inventory.getQuantity()>0)
                        .build()
                ).toList();
    }


}
