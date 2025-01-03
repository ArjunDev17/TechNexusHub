package com.codeneeti.technexushub.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiResponse {
    private boolean success;
    private Object data;
    private String message;
}
