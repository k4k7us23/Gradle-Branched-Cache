repositories {
    mavenCentral()
}

plugins {
    id("java")
    kotlin("jvm") version "2.0.21"
    id("application")
}

application {
    mainClass = "com.k4ktus23.sample.HelloKt"
}