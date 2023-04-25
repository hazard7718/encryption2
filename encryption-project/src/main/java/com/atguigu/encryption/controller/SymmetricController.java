package com.atguigu.encryption.controller;

import com.atguigu.encryption.param.*;
import com.atguigu.encryption.service.SymmetricService;
import com.atguigu.encryption.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SymmetricController {

    @Autowired
    private SymmetricService symmetricService;

    @PostMapping("symmetric/desEncrypt")
    public R desEncrypt(@RequestBody @Validated EncryptParam encryptParam, BindingResult result) {

        if (encryptParam.getPlaintext() == null || encryptParam.getPlaintext().equals("") || encryptParam.getPassword() == null) {
            return R.fail("参数异常");
        }

        return symmetricService.des(encryptParam.getPlaintext(), encryptParam.getPassword());

    }

    @PostMapping("symmetric/desDecrypt")
    public R desDecrypt(@RequestBody @Validated DecryptParam decryptParam) {
        if (decryptParam.getCiphertext() == null || decryptParam.getCiphertext().equals("")) {
            return R.fail("参数异常");
        }

        return symmetricService.desDecrypt(decryptParam.getCiphertext(), decryptParam.getPassword());
    }

    @PostMapping("getAllEncryption")
    public R getAllEncryption(@RequestBody PageParam pageParam) {
        return symmetricService.getAllEncryption(pageParam);
    }

    @PostMapping("getAllDecryption")
    public R getAllDecryption(@RequestBody PageParam pageParam) {
        return symmetricService.getAllDecryption(pageParam);
    }

    @PostMapping("irreversible/md5")
    public R md5(@RequestBody @Validated NoPasswordParam noPasswordParam) {
        if (noPasswordParam.getPlaintext() == null || noPasswordParam.getPlaintext().equals("")) {
            return R.fail("参数异常");
        }

        return symmetricService.md5(noPasswordParam.getPlaintext());
    }

    @PostMapping("irreversible/sha1")
    public R sha1(@RequestBody @Validated NoPasswordParam noPasswordParam) {
        if (noPasswordParam.getPlaintext() == null || noPasswordParam.getPlaintext().equals("")) {
            return R.fail("参数异常");
        }
        return symmetricService.sha1(noPasswordParam.getPlaintext());
    }

    @PostMapping("asymmetric/rsa/getKeys")
    public R getKeys(@RequestBody @Validated KeysParam keysParam, BindingResult result) {
        if (keysParam.getKeySize() == null) {
            return R.fail("参数异常");
        }
        return symmetricService.getKeys(keysParam.getKeySize());
    }

    @PostMapping("getAllKeys")
    public R getAllKeys(@RequestBody PageParam pageParam) {
        return symmetricService.getAllKeys(pageParam);
    }

    @PostMapping("asymmetric/rsa/encrypt")
    public R rsaEncrypt(@RequestBody @Validated AsymmetricEncryptParam asymmetricEncryptParam, BindingResult result) {
        boolean b = result.hasErrors();
        if (asymmetricEncryptParam.getPlaintext() == null || asymmetricEncryptParam.getPlaintext().equals("") ||
                asymmetricEncryptParam.getPublicKey() == null || asymmetricEncryptParam.getPublicKey().equals("")) {
            return R.fail("参数错误");
        }
        return symmetricService.rsaEncrypt(asymmetricEncryptParam);
    }

    @PostMapping("asymmetric/rsa/decrypt")
    public R rsaDecrypt(@RequestBody AsymmetricDecryptParam asymmetricDecryptParam) {
        if (asymmetricDecryptParam.getCiphertext() == null || asymmetricDecryptParam.getCiphertext().equals("") ||
                asymmetricDecryptParam.getPrivateKey() == null || asymmetricDecryptParam.getPrivateKey().equals("")) {
            return R.fail("参数错误");
        }
        return symmetricService.rsaDecrypt(asymmetricDecryptParam);
    }

    @PostMapping("asymmetric/rsa/getAllEncryption")
    public R rsaGetAllEncryption(@RequestBody PageParam pageParam) {
        return symmetricService.rsaGetAllEncryption(pageParam);
    }

    @PostMapping("asymmetric/rsa/getAllDecryption")
    public R rsaGetAllDecryption(@RequestBody PageParam pageParam) {
        return symmetricService.rsaGetAllDecryption(pageParam);
    }

}
