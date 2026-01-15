import org.jetbrains.gradle.ext.Application
import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
    id("java")
    idea
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.8"
}

group = project.property("plugin_group") as String
version = project.property("plugin_version") as String

// This snippet was taken from Mrbysco/GoneFishing
// LICENSE: https://github.com/Mrbysco/GoneFishing/blob/main/LICENSE
//
// Thjis variable should point to where your Hytale instance is installed.
val hytaleHome = "${System.getProperty("user.home")}/AppData/Roaming/Hytale"

repositories {
    mavenCentral()
}

dependencies {
    // This snippet was taken from Mrbysco/GoneFishing
    // LICENSE: https://github.com/Mrbysco/GoneFishing/blob/main/LICENSE
    //
    // This snippet adds the Hytale server jar as a Gradle dependency to allow access to its code.
    implementation(files("$hytaleHome/install/release/package/game/latest/Server/HytaleServer.jar"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

// This snippet was taken from Mrbysco/GoneFishing
// LICENSE: https://github.com/Mrbysco/GoneFishing/blob/main/LICENSE
//
// This snippet generates the `run` directory for server execution if it does not exist.
val serverRunDir = file("$projectDir/run")
if (!serverRunDir.exists()) {
    serverRunDir.mkdirs()
}

tasks.processResources {
    val properties = mapOf(
        "plugin_group" to project.property("plugin_group"),
        "plugin_version" to project.property("plugin_version"),
        "plugin_name" to project.property("plugin_name"),
        "plugin_main" to (project.findProperty("plugin_main")
            ?: "${project.property("plugin_group")}.${project.property("plugin_name")}"),
        "plugin_description" to project.property("plugin_description")
    )

    inputs.properties(properties)

    filesMatching(listOf("**/config.json", "**/manifest.json")) {
        expand(properties)
    }
}

tasks.test {
    useJUnitPlatform()
}

// This run configuration was taken from Mrbysco/GoneFishing
// LICENSE: https://github.com/Mrbysco/GoneFishing/blob/main/LICENSE
//
// This run configuration allows you to run the Hytale server directly from IntelliJ IDEA for plugin testing.
idea.project.settings.runConfigurations {
    create<Application>("HytaleServer") {
        mainClass = "com.hypixel.hytale.Main"
        moduleName = project.idea.module.name + ".main"
        programParameters = "--allow-op --assets=$hytaleHome/install/release/package/game/latest/Assets.zip"
        if (project.property("includes_pack") as String == "true") {
            programParameters += " --mods=${sourceSets.main.get().java.srcDirs.first().parentFile.absolutePath}"
        }
        if (project.property("load_user_mods") as String == "true") {
            programParameters += " --mods=$hytaleHome/UserData/Mods"
        }
        workingDirectory = serverRunDir.absolutePath
    }
}