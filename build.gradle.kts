import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.compose.compose


plugins {
    kotlin("multiplatform") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0"
    kotlin("plugin.serialization") version "1.5.31"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }}

// JVM
val ktorVersion: String by project
val kotlinxHtmlVersion: String by project
val slf4jVersion: String by project

// npm
val postcssVersion: String by project
val postcssLoaderVersion: String by project
val autoprefixerVersion: String by project
val tailwindcssVersion: String by project

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    js(IR) {
        binaries.executable()

        browser {

            commonWebpackConfig {
                cssSupport.enabled = true
            }

            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useFirefox()
                    useChromeHeadless()
                }
            }
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.ktor:ktor-server-jetty:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                //implementation("io.ktor:ktor-auth:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
                //implementation("org.slf4j:slf4j-api:$slf4jVersion")
                //runtimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-websockets:$ktorVersion")
                //implementation("org.jetbrains.exposed:exposed-core:0.36.1")
                //implementation("org.jetbrains.exposed:exposed-jdbc:0.36.1")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.256-kotlin-1.5.31")
                implementation(npm("postcss", postcssVersion))
                implementation(npm("postcss-loader", postcssLoaderVersion)) // 5.0.0 seems not to work
                implementation(npm("autoprefixer", autoprefixerVersion))
                implementation(npm("tailwindcss", tailwindcssVersion))
                //implementation(npm("@tailwindcss/jit", "0.1.18"))
            }

        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

application {
    mainClass.set("ServerKt")
}

tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack") {
    outputFileName = "js.js"
}
tasks.withType(KotlinWebpack::class.java).forEach { it.inputs.files(fileTree("src/jsMain/resources")) }

val jvmJarTask = tasks.getByName<Jar>("jvmJar") {
    dependsOn(tasks.getByName("jsBrowserProductionWebpack"))
    val jsBrowserProductionWebpack = tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack")
    from(jsBrowserProductionWebpack.destinationDirectory.resolve(jsBrowserProductionWebpack.outputFileName))
}

tasks.getByName<JavaExec>("run") {
    dependsOn(jvmJarTask)
    dependsOn()
    classpath(jvmJarTask)
}

// Suppresses a "without declaring an explicit or implicit dependency" warning
tasks.getByName("startScripts").dependsOn("metadataJar")
val copyTailwindConfig = tasks.register<Copy>("copyTailwindConfig") {
    from("./tailwind.config.js")
    into("${rootProject.buildDir}/js/packages/${rootProject.name}")

    dependsOn(":kotlinNpmInstall")
}

val copyPostcssConfig = tasks.register<Copy>("copyPostcssConfig") {
    from("./postcss.config.js")
    into("${rootProject.buildDir}/js/packages/${rootProject.name}")

    dependsOn(":kotlinNpmInstall")
}

tasks.named("processResources") {
    dependsOn(copyTailwindConfig)
    dependsOn(copyPostcssConfig)
}
tasks.withType(KotlinWebpack::class.java).forEach { t ->
    t.inputs.files(fileTree("src/jsMain/resources"))
}