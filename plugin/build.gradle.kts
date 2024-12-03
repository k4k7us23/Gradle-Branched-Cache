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
            id = "com.k4k7us23.gradlebranchedcache"
            implementationClass = "com.k4k7us23.gradlebranchedcache.GradleBranchedCachePlugin"
        }
    }
}