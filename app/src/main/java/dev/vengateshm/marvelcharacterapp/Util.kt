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

open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}