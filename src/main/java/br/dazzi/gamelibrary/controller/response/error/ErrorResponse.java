package br.dazzi.gamelibrary.controller.response.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonRootName("error")
@Getter
@Setter
public class ErrorResponse {

    private List<ErrorMessage> error_list;

    @JsonCreator
    public ErrorResponse() {
        error_list = new ArrayList<>();
    }
    public ErrorResponse(List<ErrorMessage> error_list) {
        this.error_list = List.copyOf(error_list);
    }
    public ErrorResponse(Set<ErrorMessage> error_list) {
        this.error_list = List.copyOf(error_list);
    }
}
