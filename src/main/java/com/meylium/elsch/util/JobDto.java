package com.meylium.elsch.util;

import com.meylium.elsch.model.BaseIndex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JobDto extends BaseIndex {
    private boolean status;
    private String errorCode;
    private String errorMsg;
    private Object data;
}
