package br.dazzi.gamelibrary.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Setter
@Getter
public abstract class APIException extends RuntimeException{

    private String code;
    private String message;
}
