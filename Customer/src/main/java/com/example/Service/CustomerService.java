package com.example.Service;

import com.example.DB.Customer;
import com.example.DB.Requests.CustomerRequest;
import com.example.DB.Response.CustomerResponse;
import com.example.DB.Response.FraudResponse;
import com.example.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RestTemplate restTemplate;

    public void createCustomer(CustomerRequest customerRequest) {

        Customer customer =Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .password(customerRequest.getPassword()).build();

        customerRepo.saveAndFlush(customer);
       FraudResponse fraudResponse = restTemplate.getForObject(
                "http://localhost:8081/fraud/create/{id}",
                FraudResponse.class,
                customer.getId()
        );

       if(fraudResponse.isFraud()){
           throw new IllegalStateException("Frauded");
       }

    }

    public CustomerResponse getCustomer(long id) {
        if(customerRepo.findById(id).isPresent()){
            Customer customer = customerRepo.findById(id).get();

            return  CustomerResponse.builder()
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer doesnt exist");

    }
}
