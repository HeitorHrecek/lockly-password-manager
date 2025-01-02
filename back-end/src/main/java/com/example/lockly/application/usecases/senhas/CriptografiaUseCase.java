package com.example.lockly.application.usecases.senhas;

import com.example.lockly.application.exceptions.senha.ErroCriptografarSenhaException;
import com.example.lockly.application.exceptions.senha.ErroDescriptografarSenhaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CriptografiaUseCase {

    private final PasswordEncoder passwordEncoder;

    public String criptografiaLogin(String password) {
        return passwordEncoder.encode(password);
    }

    public SenhaEChave criptografar(String senha) {
        log.info("Criptografar senha. senha={}", senha);
        try {
            SecretKey chave = gerarChave(256);
            log.info("Gerando chave secreta. chave={}", chave);
            IvParameterSpec iv = gerarIV();
            log.info("Gerenado IV. IV={}", iv);
            SenhaEChave senhaCriptografada = SenhaEChave.builder()
                    .chave(chave)
                    .senhaCriptografada(criptografarSenha(senha, chave, iv))
                    .build();

            log.info("Senha criptograda. senha={}", senhaCriptografada);

            return senhaCriptografada;
        } catch (Exception exception) {
            throw new ErroCriptografarSenhaException(exception.getMessage());
        }
    }

    public String descriptografar(String senha, SecretKey chave) {
        log.info("Descriptografar senha. senha={}", senha);
        log.info("chave={}", chave);
        String senhaDescriptograda;
        try {
             senhaDescriptograda = descriptografarSenha(senha, chave);
        } catch (Exception exception) {
            throw new ErroDescriptografarSenhaException(exception.getMessage());
        }

        log.info("Senha descriptografada. senha={}", senhaDescriptograda);

        return senhaDescriptograda;
    }

    private static SecretKey gerarChave(int n) throws Exception {
        log.info("Gerar chave secreta. n={}", n);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey chave = keyGenerator.generateKey();
        log.info("Chave secreta gerada. chave={}", chave);
        return chave;
    }

    private static IvParameterSpec gerarIV() {
        log.info("Gerando IV");
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec novoIv = new IvParameterSpec(iv);

        log.info("IV gerado. IV={}", novoIv);

        return novoIv;
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
