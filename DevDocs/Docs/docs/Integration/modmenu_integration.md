# ModMenu Integration

## Adding Via Gradle

You can either use the TerraformersMC Maven repository to your `build.gradle` file or use the existing Modrinth Maven repository.

Simplify version updates by managing the version string inside your `gradle.properties` file.  
Add this line to your `gradle.properties` file:

```properties title="gradle.properties"
modmenu_version = MOD_VERSION
```

> **⚠️️** Replace **MOD_VERSION** with your target version  
> You can find valid versions on the [ModMenu Modrinth Versions Page](https://modrinth.com/mod/modmenu/versions)

### If Choosing to Use Modrinth Maven

Add ModMenu to your dependencies in `build.gradle` file:

```groovy title="build.gradle" hl_lines="6-9"
dependencies {
    // Your other dependencies

    modApi "maven.modrinth:framinglib:${project.framinglib_version}-Fabric"

    modImplementation("maven.modrinth:modmenu:${project.modmenu_version}") {
        exclude module: "fabric-api"
        transitive = false
    }
}

```

### If Choosing to Use TerraformersMC Maven

Add TerraformersMC Maven repository to your repositories, and ModMenu to your dependencies in `build.gradle` file:

```groovy title="build.gradle" hl_lines="3 11-14"
repositories {
    maven { url = "https://api.modrinth.com/maven" }
    maven { url = "https://maven.terraformersmc.com" }
}

dependencies {
    // Your other dependencies

    modApi "maven.modrinth:framinglib:${project.framinglib_version}-Fabric"

    modImplementation("com.terraformersmc:modmenu:${project.modmenu_version}") {
        exclude module: "fabric-api"
        transitive = false
    }
}
```

## Adding ModMenu Dependency to fabric.mod.json

Add ModMenu as an optional dependency in `fabric.mod.json`:

```json title="src/main/resources/fabric.mod.json" hl_lines="4"
{
    ...
    "suggests": {
        "modmenu": ">=MOD_VERSION"
    },
    ...
}
```

> **⚠️️** Replace **MOD_VERSION** with your target version  
> You can find valid versions on the [ModMenu Modrinth Versions Page](https://modrinth.com/mod/modmenu/versions)

## Creating ModMenu Entry Point

Create a class that is implementing `ModMenuApi` and override the `getModConfigScreenFactory()` method to provide our screen.

```java title="java"
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> example_mod.getLayoutConfigScreenBuilder().build();
    }

}
```

## Adding ModMenu Entry Point to fabric.mod.json

Add your entry point to `fabric.mod.json`:

```json  title="src/main/resources/fabric.mod.json" hl_lines="10-12"
{
    ...
    "entrypoints": {
        "main": [
            "example_mod.Main"
        ],
        "client": [
            "example_mod.ClientMain"
        ],
        "modmenu": [
            "example_mod.config.ModMenuIntegration"
        ]
    },
    ...
}
```
