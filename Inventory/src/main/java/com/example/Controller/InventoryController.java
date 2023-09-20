package com.example.Controller;

import com.example.DB.Request.InventoryRequest;
import com.example.DB.Response.InventoryResponse;
import com.example.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> check(@RequestParam List<String> code){
      return inventoryService.check(code);
    }
}
