
# Setting-up FramingLib

## Adding Via Gradle

Add the Modrinth Maven repository to your `build.gradle` file:

```groovy
repositories {
    maven { url = "https://api.modrinth.com/maven" }
}
```

Then add the library to your dependencies in `build.gradle` file:

```groovy hl_lines="4"
dependencies {
    // Your other dependencies

    modApi "maven.modrinth:framinglib:MOD_VERSION"
}
```

> **⚠️️** Replace **MOD_VERSION** with your target version  
> Find valid versions on the [FramingLib Modrinth Versions Page](https://modrinth.com/mod/framinglib/versions)

## Examples

### Fabric Setup

`build.gradle`

```groovy hl_lines="10"
repositories {
    maven { url = "https://api.modrinth.com/maven" }
}

dependencies {
    minecraft "net.minecraft:minecraft:1.16.5"
    mappings loom.officialMojangMappings()
    modImplementation "net.fabricmc:fabric-loader:0.19.3"

    modApi "maven.modrinth:framinglib:1.0.0+1.16.5-Fabric"
}
```

### Forge Setup

`build.gradle`

```groovy hl_lines="10"
repositories {
    maven { url = "https://api.modrinth.com/maven" }
}

dependencies {
    minecraft "net.minecraft:minecraft:1.16.5"
    mappings loom.officialMojangMappings()
    forge "net.minecraftforge:forge:1.16.5-36.2.34"

    modApi "maven.modrinth:framinglib:1.0.0+1.16.5-Forge"
}
```

## Tip: Dependency Properties

Simplify version updates by managing the version string inside your `gradle.properties` file.

### Configure Properties

Add this line to your `gradle.properties` file:

```properties
framinglib_version = 1.0.0+1.16.5
```

### Configure Build Script

Update your `build.gradle` file to reference the property:

```groovy hl_lines="6 8"
dependencies {
    // Your other dependencies

    // Reads version from gradle.properties
    // For Fabric
    modApi "maven.modrinth:framinglib:${project.framinglib_version}-Fabric"
    // For Forge
    modApi "maven.modrinth:framinglib:${project.framinglib_version}-Forge"
}
```
