package com.atguigu.encryption.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("asymmetric_decryption")
public class AsymmetricDecryption {

    public AsymmetricDecryption(String ciphertext, String privateKey, String plaintext, String method) {
        this.ciphertext = ciphertext;
        this.privateKey = privateKey;
        this.plaintext = plaintext;
        this.method = method;
    }

    public AsymmetricDecryption() {
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank
    private String ciphertext;

    @NotBlank
    private String privateKey;

    @NotBlank
    private String plaintext;

    @NotBlank
    private String method;
}
