package com.example.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDetailsResponseDto implements Serializable {

    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    public static final int UNAVAILABLE = 2;
    public static final int PARTIAL_RESPONSE = 3;
    private static final long serialVersionUID = -611724979737795208L;
    private Integer statusCode;
    private String message;

}
