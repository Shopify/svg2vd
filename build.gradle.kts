import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.21"
    application
}

val mainClass = "com.shopify.svg2vd.Svg2VdKt"

group = rootProject.name
version = "0.1"

repositories {
    google()
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile ("com.github.ajalt:clikt:1.7.0")

    compile("com.android.tools:sdk-common:26.3.2")
    implementation("com.android.tools:common:26.3.2")

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = mainClass
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = mainClass
    }

    from(configurations.runtime.map {
        if (it.isDirectory) it else zipTree(it)
    })

    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}
