package com.example.ticketsale.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseSuccess {

    public ResponseSuccess(String value) {
        result = value;
    }

    public String result;
}
