package com.example.ticketsale.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@AllArgsConstructor
public class Response {

    public String result;

    public String error;

    public static Response success(String value) {
        return new Response(value, null);
    }

    public static Response error(String value) {
        return new Response(null, value);
    }
}
