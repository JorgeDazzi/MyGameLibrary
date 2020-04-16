package br.dazzi.gamelibrary.controller.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int code;
    private String description;
    private String message;
}


