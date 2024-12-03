repositories {
    mavenCentral()
}

plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("java-gradle-plugin")
}

gradlePlugin {
    plugins {
        create("gradleBranchedCache") {
            id = "io.github.k4k7us23.gradlebranchedcache"
            implementationClass = "io.github.k4k7us23.gradlebranchedcache.GradleBranchedCachePlugin"
        }
    }
}