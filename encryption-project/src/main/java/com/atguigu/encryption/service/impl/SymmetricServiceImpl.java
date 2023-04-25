package com.atguigu.encryption.service.impl;

import com.atguigu.encryption.mapper.*;
import com.atguigu.encryption.param.AsymmetricDecryptParam;
import com.atguigu.encryption.param.AsymmetricEncryptParam;
import com.atguigu.encryption.param.PageParam;
import com.atguigu.encryption.pojo.*;
import com.atguigu.encryption.service.SymmetricService;
import com.atguigu.encryption.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SymmetricServiceImpl implements SymmetricService {

    @Autowired
    private EncryptMapper encryptMapper;

    @Autowired
    private DecryptMapper decryptMapper;

    @Autowired
    private KeysMapper keysMapper;

    @Autowired
    private AsymmetricEncryptMapper asymmetricEncryptMapper;

    @Autowired
    private AsymmetricDecryptMapper asymmetricDecryptMapper;

    @Override
    public R des(String plaintext, String password) {
        try {

            String ciphertext = Des.encrypt(plaintext, password);

            Encryption encryption = new Encryption(plaintext, ciphertext, password, "DES");

            int rows = encryptMapper.insert(encryption);
            if (rows == 0) {
                return R.fail("添加数据库失败");
            }

            return R.ok("转换成功", ciphertext);

        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            return R.fail("转换失败");
        }
    }

    @Override
    public R desDecrypt(String ciphertext, String password) {
        try {


            String plaintext = Des.decrypt(ciphertext, password);

            Decryption decryption = new Decryption(ciphertext, plaintext, password, "DES");


            int rows = decryptMapper.insert(decryption);
            if (rows == 0) {
                return R.fail("添加数据库失败");
            }

            return R.ok("转换成功", plaintext);

        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            return R.fail("转换失败");
        }
    }

    @Override
    public R getAllEncryption(PageParam pageParam) {
        QueryWrapper<Encryption> queryWrapper = new QueryWrapper<>();
        Integer current = pageParam.getCurrent();
        Integer size = pageParam.getSize();
        current = current == null ? 1:current;
        size = size == null ? 5:size;
        IPage<Encryption> page = new Page<>(current, size);
        page = encryptMapper.selectPage(page, queryWrapper);
        List<Encryption> encryptions = page.getRecords();
        long total = page.getTotal();
        if (encryptions == null || encryptions.size() == 0) {
            return R.fail("查询失败");
        }
        return R.ok("查询成功", encryptions, total);

    }

    @Override
    public R getAllDecryption(PageParam pageParam) {
        QueryWrapper<Decryption> queryWrapper = new QueryWrapper<>();
        Integer current = pageParam.getCurrent();
        Integer size = pageParam.getSize();
        current = current == null ? 1:current;
        size = size == null ? 5:size;
        IPage<Decryption> page = new Page<>(current, size);
        page = decryptMapper.selectPage(page, queryWrapper);
        List<Decryption> decryptions = page.getRecords();
        long total = page.getTotal();
        if (decryptions == null || decryptions.size() == 0) {
            return R.fail("查询失败");
        }
        return R.ok("查询成功", decryptions, total);
    }

    @Override
    public R md5(String plaintext) {
        String ciphertext = MD5.getMD5Code(plaintext);
        Encryption encryption = new Encryption(plaintext, ciphertext, null, "MD5");
        int rows = encryptMapper.insert(encryption);
        if (rows == 0) {
            return R.fail("添加数据库失败");
        }
        return R.ok("转换成功", ciphertext);
    }

    @Override
    public R sha1(String plaintext) {
        String ciphertext = SHA1.getSha1(plaintext);
        Encryption encryption = new Encryption(plaintext, ciphertext, null, "SHA1");
        int rows = encryptMapper.insert(encryption);
        if (rows == 0) {
            return R.fail("添加数据库失败");
        }
        return R.ok("转换成功", ciphertext);
    }

    @Override
    public R getKeys(Integer keySize) {
        // 生成密钥对
        Map<String, Object> keyMap = null;
        try {
            keyMap = RSAUtil.initKey(keySize);
            String publicKey = RSAUtil.getPublicKeyStr(keyMap);
            log.info("公钥:[{}]，长度:[{}]", publicKey, publicKey.length());
            String privateKey = RSAUtil.getPrivateKeyStr(keyMap);
            log.info("私钥:[{}]，长度:[{}]", privateKey, privateKey.length());

            Keys keys = new Keys(keySize, publicKey, privateKey, "RSA");
            int rows = keysMapper.insert(keys);
            if (rows == 0) {
                return R.fail("添加数据库失败");
            }

            return R.ok("转换成功", keys);

        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("生成密钥对失败");
        }

    }

    @Override
    public R getAllKeys(PageParam pageParam) {
        Integer size = pageParam.getSize();
        Integer current = pageParam.getCurrent();
        current = current == null ? 1:current;
        size = size == null ? 5:size;

        QueryWrapper<Keys> queryWrapper = new QueryWrapper<>();
        IPage<Keys> page = new Page<>(current, size);
        page = keysMapper.selectPage(page, queryWrapper);
        List<Keys> keys = page.getRecords();
        long total = page.getTotal();
        if (keys == null || keys.size() == 0) {
            return R.fail("查询失败");
        }

        return R.ok("查询成功", keys, total);
    }

    @Override
    public R rsaEncrypt(AsymmetricEncryptParam asymmetricEncryptParam) {
        String plaintext = asymmetricEncryptParam.getPlaintext();
        String publicKey = asymmetricEncryptParam.getPublicKey();
        String ciphertext = RSAUtil.encrypt1(plaintext, publicKey);
        AsymmetricEncryption asymmetricEncryption = new AsymmetricEncryption(plaintext, publicKey, ciphertext, "RSA");
        int rows = asymmetricEncryptMapper.insert(asymmetricEncryption);
        if (rows == 0) {
            return R.fail("添加数据库失败");
        }

        return R.ok("加密成功", ciphertext);
    }

    @Override
    public R rsaDecrypt(AsymmetricDecryptParam asymmetricDecryptParam) {
        String ciphertext = asymmetricDecryptParam.getCiphertext();
        String privateKey = asymmetricDecryptParam.getPrivateKey();
        String plaintext = RSAUtil.decrypt1(ciphertext, privateKey);

        AsymmetricDecryption asymmetricDecryption = new AsymmetricDecryption(ciphertext, privateKey, plaintext, "RSA");
        int rows = asymmetricDecryptMapper.insert(asymmetricDecryption);
        if (rows == 0) {
            return R.fail("添加数据库失败");
        }

        return R.ok("解密成功", plaintext);
    }

    @Override
    public R rsaGetAllEncryption(PageParam pageParam) {
        Integer size = pageParam.getSize();
        Integer current = pageParam.getCurrent();
        current = current == null ? 1:current;
        size = size == null ? 5:size;

        QueryWrapper<AsymmetricEncryption> queryWrapper = new QueryWrapper<>();
        IPage<AsymmetricEncryption> page = new Page<>(current, size);
        page = asymmetricEncryptMapper.selectPage(page, queryWrapper);
        List<AsymmetricEncryption> asymmetricEncryptions = page.getRecords();
        long total = page.getTotal();
        if (asymmetricEncryptions == null || asymmetricEncryptions.size() == 0) {
            return R.fail("查询失败");
        }
        return R.ok("查询成功", asymmetricEncryptions, total);
    }

    @Override
    public R rsaGetAllDecryption(PageParam pageParam) {
        Integer size = pageParam.getSize();
        Integer current = pageParam.getCurrent();
        current = current == null ? 1:current;
        size = size == null ? 5:size;

        QueryWrapper<AsymmetricDecryption> queryWrapper = new QueryWrapper<>();
        IPage<AsymmetricDecryption> page = new Page<>(current, size);
        page = asymmetricDecryptMapper.selectPage(page, queryWrapper);
        List<AsymmetricDecryption> asymmetricDecryptions = page.getRecords();
        long total = page.getTotal();
        if (asymmetricDecryptions == null || asymmetricDecryptions.size() == 0) {
            return R.fail("查询失败");
        }
        return R.ok("查询成功", asymmetricDecryptions, total);
    }
}
