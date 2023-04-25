package com.atguigu.encryption.service;

import com.atguigu.encryption.param.AsymmetricDecryptParam;
import com.atguigu.encryption.param.AsymmetricEncryptParam;
import com.atguigu.encryption.param.PageParam;
import com.atguigu.encryption.pojo.Encryption;
import com.atguigu.encryption.utils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SymmetricService {
    R des(String plaintext, String password);

    R desDecrypt(String ciphertext, String password);

    R getAllEncryption(PageParam pageParam);

    R getAllDecryption(PageParam pageParam);

    R md5(String plaintext);

    R sha1(String plaintext);

    R getKeys(Integer keySize);

    R getAllKeys(PageParam pageParam);

    R rsaEncrypt(AsymmetricEncryptParam asymmetricEncryptParam);

    R rsaDecrypt(AsymmetricDecryptParam asymmetricDecryptParam);

    R rsaGetAllEncryption(PageParam pageParam);

    R rsaGetAllDecryption(PageParam pageParam);
}
