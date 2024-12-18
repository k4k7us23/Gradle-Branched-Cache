package io.github.k4k7us23.gradlebranchedcache

import io.github.k4k7us23.gradlebranchedcache.vcs.BranchCacheKeyProvider
import io.github.k4k7us23.gradlebranchedcache.vcs.BranchNameProvider
import io.github.k4k7us23.gradlebranchedcache.vcs.GitVCS
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.caching.http.HttpBuildCache
import java.net.URI

class GradleBranchedCachePlugin : Plugin<Settings> {

    private lateinit var extension: GradleBranchedCachePluginExtension

    override fun apply(settings: Settings) {
        extension =
            settings.extensions.create("branchedRemoteBuildCache", GradleBranchedCachePluginExtension::class.java)

        val branchCacheKeyProvider = BranchCacheKeyProvider(
            branchNameProvider = BranchNameProvider(
                vcs = GitVCS(),
                extension = extension,
            )
        )

        settings.gradle.settingsEvaluated {
            settings.buildCache { buildCache ->
                buildCache.remote(HttpBuildCache::class.java) { remote ->
                    remote.url = getFinalRemoteUrl(branchCacheKeyProvider)
                    extension.push?.let(remote::setPush)

                    extension.enabled?.let(remote::setEnabled)
                    extension.allowUntrustedServer?.let(remote::setAllowUntrustedServer)
                    extension.allowInsecureProtocol?.let(remote::setAllowInsecureProtocol)
                    extension.useExpectContinue?.let(remote::setUseExpectContinue)

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
    private fun getFinalRemoteUrl(branchCacheKeyProvider: BranchCacheKeyProvider): URI {
        val baseUrl = URI(requireNotNull(extension.baseUrl))
        return baseUrl.resolve(branchCacheKeyProvider.getKey())
    }

}