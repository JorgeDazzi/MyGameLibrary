package br.dazzi.gamelibrary.controller.response.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("error")
public class ErrorResponse {

    private List<ErrorMessage> error;

    @JsonCreator
    public ErrorResponse() {
        error = new ArrayList<>();
    }

    public ErrorResponse(List<ErrorMessage> error) {
        this.error = List.copyOf(error);
    }

    public List<ErrorMessage> getError() {
        return error;
    }

    public void setError(List<ErrorMessage> error) {
        this.error = error;
    }
}
