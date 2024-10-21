package com.example.lockly.serviceLayer.passwords;

import lombok.Builder;

import javax.crypto.SecretKey;

@Builder
public record PasswordAndKey(SecretKey key, String passswordEncrypt) {}
