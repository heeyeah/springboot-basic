package com.hee.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
    private String whoAmI;
    private int errCode;
    private String errMsg;
    private String[] stackTrace;
}
