package com.example.ticketsale.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseError {

    public ResponseError(String value) {
        error = value;
    }

    public String error;
}
