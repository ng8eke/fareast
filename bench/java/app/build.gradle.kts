/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.3/userguide/building_java_projects.html
 */

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm").version("1.5.30")
    id("com.github.ben-manes.versions").version("0.39.0")
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.ng8eke:Fareast:master-SNAPSHOT")
    implementation("com.spotify:annoy:0.2.6")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    // Define the main class for the application.
    mainClassName = "ru.annoy.test.AppKt"
}

tasks.register("du") { dependsOn("dependencyUpdates") }

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
    rejectVersionIf { isNonStable(candidate.version) && !isNonStable(currentVersion) }
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
