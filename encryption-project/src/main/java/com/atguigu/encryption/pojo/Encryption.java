package com.atguigu.encryption.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("encryption")
public class Encryption {

    public Encryption(String plaintext, String ciphertext, String password, String method) {
        this.plaintext = plaintext;
        this.ciphertext = ciphertext;
        this.password = password;
        this.method = method;
    }

    public Encryption() {
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String plaintext;

    private String ciphertext;

    private String password;

    private String method;
}
