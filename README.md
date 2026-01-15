# Hytale Plugin Template

A minimal, ready-to-use template for creating Hytale plugins.

## Features

✅ **Modern Build System** - Gradle with Kotlin DSL  
✅ **Automated Testing** - Custom Gradle plugin for one-command server testing  
✅ **Minimal Structure** - Only essential files, write your own code

---

## Quick Start

### Prerequisites

- **Java 25 JDK** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- **IntelliJ IDEA** - [Download here](https://www.jetbrains.com/idea/download/) (Community Edition is fine)
- **Git** - [Download here](https://git-scm.com/)

### 1. Click `Use this Template` and select `Create a new repository`

You can also just click [here](https://github.com/new?template_name=HytaleTemplatePlugin&template_owner=TechTastic) for a shortcut.
This will create a new repository using this one as its template.

### 2. Clone Your New Repository

```bash
git clone https://github.com/YourUsername/YourRepoName.git
cd YourRepoName
```

### 3. Build and Run

This base template should work already. To build it, simply run `./gradlew build` in the terminal or the Gradle `Build` task in IntelliJ.
To test it on a local Hytale server, use the `HytaleServer` run configuration in IntelliJ.

All built JARs will be located in `build/libs`.

Its always a good idea to test your plugin often to ensure everything is working as expected.

### 4. Customization

After verifying that my templaye works, you can start customizing it:

**`settings.gradle.kts`:**
```kotlin
rootProject.name = "your-plugin-name"
```

**`gradle.properties`:**
```properties
plugin_group = com.example
plugin_version = 1.0.0
plugin_name = YourPluginName
plugin_description = A brief description of your plugin.
```
You can also tell IntelliJ to include the associated pack (`includes_pack`) for your plugin as well as any mods (`load_user_mods`) you may already have installed when attempting ot run the local server.
You can *also* specify a different main class by setting the `plugin_main` property to the classpath of the class.

**Renaming the appropriate classes:**
- Rename `src/main/java/com/example/TemplatePlugin.java` to match your `plugin_name` in `gradle.properties`.
- Update its package name to match your `plugin_group` in `gradle.properties`.

### 5. Further Development

You can now start adding your own functionality to the plugin by editing the Java files in `src/main/java`.
Don't forget to periodically test out your plugin using the server!

Sadly, documentation is sparse and unofficial documentation is either AI generated or in the works, so I won't be linking to them at the moment.