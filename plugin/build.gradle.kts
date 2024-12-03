import com.vanniktech.maven.publish.SonatypeHost

repositories {
    mavenCentral()
}

plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("java-gradle-plugin")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

gradlePlugin {
    plugins {
        create("gradleBranchedCache") {
            id = "io.github.k4k7us23.gradlebranchedcache"
            implementationClass = "io.github.k4k7us23.gradlebranchedcache.GradleBranchedCachePlugin"
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}