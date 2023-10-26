package dev.vengateshm.marvelcharacterapp

import java.math.BigInteger
import java.security.MessageDigest

fun getHash(timestamp: String, privateKey: String, publicKey: String): String {
    val hashString = buildString {
        append(timestamp)
        append(privateKey)
        append(publicKey)
    }
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(hashString.toByteArray()))
        .toString(16)
        .padStart(32, '0')
}