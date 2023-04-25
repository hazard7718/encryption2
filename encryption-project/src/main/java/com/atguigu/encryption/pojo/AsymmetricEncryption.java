package com.atguigu.encryption.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("asymmetric_encryption")
public class AsymmetricEncryption{

    public AsymmetricEncryption(String plaintext, String publicKey, String ciphertext, String method) {
        this.plaintext = plaintext;
        this.publicKey = publicKey;
        this.ciphertext = ciphertext;
        this.method = method;
    }

    public AsymmetricEncryption() {
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank
    private String plaintext;

    @NotBlank
    private String publicKey;

    @NotBlank
    private String ciphertext;

    @NotBlank
    private String method;
}
