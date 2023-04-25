package com.atguigu.encryption.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EncryptParam {

    @JsonProperty("data")
    @NotBlank
    private String plaintext;

    @NotBlank
    private String password;
}
