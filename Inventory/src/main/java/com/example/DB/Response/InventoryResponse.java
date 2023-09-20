package com.example.DB.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class InventoryResponse {
    private String code;
    private Boolean exist;
}
