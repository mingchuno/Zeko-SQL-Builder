/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("com.diffplug.spotless") version "6.22.0"
}

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    implementation("io.vertx:vertx-core:4.3.8")
    implementation("joda-time:joda-time:2.9.7")
    implementation("io.zeko:zeko-data-mapper:1.6.7")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.20")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.19")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.19")
    compileOnly("io.vertx:vertx-jdbc-client:4.3.8")
    compileOnly("com.github.jasync-sql:jasync-postgresql:1.2.3")
    compileOnly("com.github.jasync-sql:jasync-mysql:1.2.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
    compileOnly("com.zaxxer:HikariCP:5.0.1")
    compileOnly("io.vertx:vertx-lang-kotlin:4.3.8")
}

tasks.withType<Test>().configureEach { useJUnitPlatform() }

spotless { kotlin { ktfmt("0.46").kotlinlangStyle() } }

group = "io.zeko"

version = "1.5.0-SNAPSHOT"

description = "io.zeko:zeko-sql-builder"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

java { toolchain.languageVersion.set(JavaLanguageVersion.of(11)) }

kotlin { explicitApi() }

tasks.withType<KotlinCompile>().configureEach { kotlinOptions { jvmTarget = "11" } }

publishing { publications.create<MavenPublication>("maven") { from(components["java"]) } }

tasks.withType<JavaCompile>() { options.encoding = "UTF-8" }

tasks.withType<Javadoc>() { options.encoding = "UTF-8" }
