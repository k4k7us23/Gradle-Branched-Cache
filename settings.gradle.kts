rootProject.name = "gradle-branched-cache"

pluginManagement {
    includeBuild("plugin")
}

plugins {
    id("io.github.k4k7us23.gradlebranchedcache")
}

includeBuild("sample")

branchedRemoteBuildCache {
    baseUrl = "http://localhost/cache/"
    push = true
    allowInsecureProtocol = true
    credentials {
        username = "user"
        password = "password"
    }
}