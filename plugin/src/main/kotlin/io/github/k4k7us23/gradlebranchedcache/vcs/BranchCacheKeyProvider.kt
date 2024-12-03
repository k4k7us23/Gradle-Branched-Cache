package io.github.k4k7us23.gradlebranchedcache.vcs

import java.security.MessageDigest

class BranchCacheKeyProvider(
    private val branchNameProvider: BranchNameProvider
) {

    // TODO: implement unit-test
    fun getKey(): String {
        return sha1(branchNameProvider.getBranchName())
    }

    private fun sha1(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-1")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}