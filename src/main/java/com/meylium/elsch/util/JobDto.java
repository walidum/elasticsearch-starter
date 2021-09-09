package com.meylium.elsch.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JobDto {
    private boolean status;
    private String errorCode;
    private String errorMsg;
    private Object data;
}
