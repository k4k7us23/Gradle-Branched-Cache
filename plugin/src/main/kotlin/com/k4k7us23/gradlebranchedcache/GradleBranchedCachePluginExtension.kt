package com.k4k7us23.gradlebranchedcache

import org.gradle.api.Action
import org.gradle.caching.http.HttpBuildCacheCredentials

open class GradleBranchedCachePluginExtension {

    var enabled: Boolean? = null
    var push: Boolean? = null

    var baseUrl: String? = null
    var allowUntrustedServer: Boolean? = null
    var allowInsecureProtocol: Boolean? = null
    var useExpectContinue: Boolean? = null

    val credentials: HttpBuildCacheCredentials = HttpBuildCacheCredentials()

    fun credentials(action: Action<HttpBuildCacheCredentials>) {
        action.execute(credentials)
    }
}