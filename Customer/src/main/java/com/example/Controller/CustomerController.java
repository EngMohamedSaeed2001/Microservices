package com.example.Controller;

import com.example.DB.Requests.CustomerRequest;
import com.example.DB.Response.CustomerResponse;
import com.example.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public void createCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
    }

    @GetMapping("/get/{id}")
    public CustomerResponse getCustomerService(@PathVariable long id) {
        return customerService.getCustomer(id);
    }
}
