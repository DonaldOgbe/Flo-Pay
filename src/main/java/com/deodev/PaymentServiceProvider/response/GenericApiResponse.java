package com.deodev.PaymentServiceProvider.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class GenericApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
