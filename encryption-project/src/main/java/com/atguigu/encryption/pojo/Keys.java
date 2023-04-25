package com.atguigu.encryption.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@TableName("size_and_keys")
public class Keys {

    public Keys(Integer keySize, String publicKey, String privateKey, String method) {
        this.keySize = keySize;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.method = method;
    }

    public Keys() {
    }

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotNull
    private Integer keySize;

    @NotBlank
    private String publicKey;

    @NotBlank
    private String privateKey;

    @NotBlank
    private String method;
}
