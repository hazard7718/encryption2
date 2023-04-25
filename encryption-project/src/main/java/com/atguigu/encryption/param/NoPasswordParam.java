package com.atguigu.encryption.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoPasswordParam {

    @NotBlank
    private String plaintext;
}
