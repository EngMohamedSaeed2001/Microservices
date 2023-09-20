package com.example.DB.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CustomerResponse {
    private String name;
    private String email;
}
