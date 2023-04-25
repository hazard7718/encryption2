package com.atguigu.encryption.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KeysParam {

    @NotNull
    private Integer keySize;
}
