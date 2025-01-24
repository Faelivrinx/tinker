package com.dominikdev.tinker

import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@ConfigurationProperties(prefix = "rsa-config")
class RsaConfig(var publicKey: String, var privateKey: String) {

    fun getPublicKey(): RSAPublicKey? {
        return runCatching {
            val keyBytes = Base64.getDecoder().decode(publicKey)
            val keyFactory = KeyFactory.getInstance("RSA")
            return keyFactory.generatePublic(X509EncodedKeySpec(keyBytes)) as RSAPublicKey
        }.getOrElse {
            // Log the exception or handle it appropriately
            null
        }
    }

    fun getPrivateKey(): RSAPrivateKey? {
        return kotlin.runCatching {
            val keyBytes = Base64.getDecoder().decode(privateKey)
            val keyFactory = KeyFactory.getInstance("RSA")
            return keyFactory.generatePrivate(PKCS8EncodedKeySpec(keyBytes)) as RSAPrivateKey
        }.getOrElse {
            // Log the exception or handle it appropriately
            null
        }
    }
}