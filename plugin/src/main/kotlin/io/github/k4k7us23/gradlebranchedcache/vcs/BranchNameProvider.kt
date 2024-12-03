package io.github.k4k7us23.gradlebranchedcache.vcs

import io.github.k4k7us23.gradlebranchedcache.GradleBranchedCachePluginExtension

class BranchNameProvider(
    private val vcs: IVCS,
    private val extension: GradleBranchedCachePluginExtension,
) {

    fun getBranchName(): String {
        val extensionBranchName = extension.branchNameOverride
        return if (extensionBranchName == null) {
            vcs.getBranch()
        } else {
            extensionBranchName
        }
    }
}