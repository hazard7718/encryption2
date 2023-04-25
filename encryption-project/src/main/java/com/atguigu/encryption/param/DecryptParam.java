package com.atguigu.encryption.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DecryptParam {

    @JsonProperty("data")
    @NotBlank
    private String ciphertext;

    @NotBlank
    private String password;
}
