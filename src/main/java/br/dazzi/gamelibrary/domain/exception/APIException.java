package br.dazzi.gamelibrary.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Setter
@Getter
public abstract class APIException extends RuntimeException{
    private int code;
    private String description;
    private String message;
}
