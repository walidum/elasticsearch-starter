package com.meylium.elsch.util;

import com.meylium.elsch.model.BaseIndex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto extends BaseIndex {
    private String name;
    private String displayName;
}
