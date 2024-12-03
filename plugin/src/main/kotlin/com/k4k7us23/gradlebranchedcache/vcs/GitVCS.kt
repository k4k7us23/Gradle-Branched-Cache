package com.k4k7us23.gradlebranchedcache.vcs

// TODO implement unit-test
class GitVCS : IVCS {

    override fun getBranch(): String {
        return executeCommandUsingProcessBuilder(listOf("git", "rev-parse", "--abbrev-ref", "HEAD"))
    }

    private fun executeCommandUsingProcessBuilder(command: List<String>): String {
        return try {
            val process = ProcessBuilder(command)
                .redirectErrorStream(true) // Redirect error stream to output
                .start()
            val output = process.inputStream.bufferedReader().readText()
            process.waitFor() // Wait for the process to complete
            output
        } catch (e: Exception) {
            e.printStackTrace()
            "Error executing command: ${e.message}"
        }
    }
}