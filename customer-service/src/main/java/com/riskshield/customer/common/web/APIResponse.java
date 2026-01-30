package com.riskshield.customer.common.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    private APIStatus status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}
