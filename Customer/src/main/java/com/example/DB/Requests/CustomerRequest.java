package com.example.DB.Requests;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String email;
    private String password;
}
