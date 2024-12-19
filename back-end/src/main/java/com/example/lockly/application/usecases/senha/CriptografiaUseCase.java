package com.example.lockly.application.usecases.senha;

import com.example.lockly.application.exceptions.senha.ErroDescriptografarSenhaException;
import com.example.lockly.application.exceptions.senha.ErroCriptografarSenhaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class CriptografiaUseCase {

    private final PasswordEncoder passwordEncoder;

    public String criptografiaLogin(String password) {
        return passwordEncoder.encode(password);
    }

    public SenhaEChave criptografar(String password) {
        try {
            SecretKey key = gerarChave(256);
            IvParameterSpec iv = gerarIV();
            return SenhaEChave.builder().chave(key).senhaCriptografada(criptografarSenha(password, key, iv)).build();
        }catch (Exception exception) {
            throw new ErroCriptografarSenhaException(exception.getMessage());
        }
    }

    public String descriptografar(String password, SecretKey key) {
        try {
            return descriptografarSenha(password, key);
        }catch (Exception exception) {
            throw new ErroDescriptografarSenhaException(exception.getMessage());
        }
    }

    private static SecretKey gerarChave(int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    private static IvParameterSpec gerarIV() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static String criptografarSenha(String plainText, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
        byte[] ivAndCipherText = new byte[iv.getIV().length + cipherText.length];
        System.arraycopy(iv.getIV(), 0, ivAndCipherText, 0, iv.getIV().length);
        System.arraycopy(cipherText, 0, ivAndCipherText, iv.getIV().length, cipherText.length);
        return Base64.getEncoder().encodeToString(ivAndCipherText);
    }

    private static String descriptografarSenha(String cipherText, SecretKey key) throws Exception {
        byte[] ivAndCipherText = Base64.getDecoder().decode(cipherText);
        byte[] iv = new byte[16];
        System.arraycopy(ivAndCipherText, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        byte[] actualCipherText = new byte[ivAndCipherText.length - iv.length];
        System.arraycopy(ivAndCipherText, iv.length, actualCipherText, 0, actualCipherText.length);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] plainText = cipher.doFinal(actualCipherText);
        return new String(plainText, "UTF-8");
    }
}
