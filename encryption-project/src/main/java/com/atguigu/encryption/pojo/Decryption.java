package com.atguigu.encryption.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("decryption")
public class Decryption {

    public Decryption(String ciphertext, String plaintext, String password, String method) {
        this.ciphertext = ciphertext;
        this.plaintext = plaintext;
        this.password = password;
        this.method = method;
    }

    public Decryption() {
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String ciphertext;

    private String plaintext;

    private String password;

    private String method;
}
