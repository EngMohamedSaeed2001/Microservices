package com.example.Configuration;

import com.example.DB.Inventory;
import com.example.Repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public  class Startup implements CommandLineRunner {
    @Autowired
    InventoryRepo inventoryRepo;

    @Override
    public void run(String... args) throws Exception {
        if(inventoryRepo.findAll().isEmpty()){
            for (int i = 0; i < 3; i++) {
                Inventory inventory =Inventory.builder()
                        .quantity(3*i+1)
                        .skcode("P"+i)
                        .build();
                inventoryRepo.save(inventory);
            }
        }
    }


}
