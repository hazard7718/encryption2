package com.atguigu.encryption.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AsymmetricDecryptParam {
    @NotBlank
    private String ciphertext;
    @NotBlank
    private String privateKey;
}
