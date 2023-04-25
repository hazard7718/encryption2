package com.atguigu.encryption.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AsymmetricEncryptParam {
    @NotBlank
    private String plaintext;

    @NotBlank
    private String publicKey;
}
