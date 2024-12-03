package com.k4k7us23.gradlebranchedcache

import com.k4k7us23.gradlebranchedcache.vcs.BranchCacheKeyProvider
import com.k4k7us23.gradlebranchedcache.vcs.GitVCS
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.caching.http.HttpBuildCache
import java.net.URI

class GradleBranchedCachePlugin : Plugin<Settings> {

    private val branchKeyProvider = BranchCacheKeyProvider(
        vcs = GitVCS()
    )

    private lateinit var extension: GradleBranchedCachePluginExtension

    override fun apply(settings: Settings) {
        extension =
            settings.extensions.create("branchedRemoteBuildCache", GradleBranchedCachePluginExtension::class.java)

        settings.gradle.settingsEvaluated {
            settings.buildCache { buildCache ->
                buildCache.remote(HttpBuildCache::class.java) { remote ->
                    remote.url = getFinalRemoteUrl()
                    extension.push?.let(remote::setPush)

                    extension.enabled?.let(remote::setEnabled)
                    extension.allowUntrustedServer?.let(remote::setAllowUntrustedServer)
                    extension.allowInsecureProtocol?.let(remote::setAllowInsecureProtocol)

                    extension.credentials.let { extensionCredentials ->
                        val remoteCredentials = remote.credentials
                        remoteCredentials.username = extensionCredentials.username
                        remoteCredentials.password = extensionCredentials.password
                    }
                }
            }
        }
    }

    // TODO check base url is not empty
    // TODO support no slash at the end
    private fun getFinalRemoteUrl(): URI {
        val baseUrl = URI(requireNotNull(extension.baseUrl))
        return baseUrl.resolve(branchKeyProvider.getKey())
    }

}