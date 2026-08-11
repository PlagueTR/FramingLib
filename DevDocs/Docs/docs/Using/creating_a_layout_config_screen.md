# Creating a Layout Config Screen

To create a layout config screen, you must use the layout config screen builder.

## Initialize the Builder

Instantiate a new `LayoutConfigScreenBuilder` by calling the `create()` method.

```java
LayoutConfigScreenBuilder configBuilder = LayoutConfigScreenBuilder.create();
```

> **⚠️️** You must create a new builder instance **every single time** you open the layout config screen.

Chain methods together to define the screen's core properties, such as the parent screen and the title.

```java
configBuilder
    .setParentScreen(Minecraft.getInstance().screen)
    .setTitle(new TranslatableComponent("text.example_mod.config_title"));
```

- **Parent Screen**: the screen to be opened when the user closes the layout config screen. Pass the currently active screen for most standard use cases.

- **Title**: Automatically translated based on the user's selected language. Ensure you add the corresponding translation key to your mod's language `.json` files.

## Building the Screeen

You can build the layout config screen using `LayoutConfigScreenBuilder.build()`.

```java
Screen layoutConfigScreen = configBuilder.build();
```

You can learn more about `LayoutConfigScreenBuilder` at [LayoutConfigScreenBuilder API Reference page](../APIReference/layout_config_screen_builder.md)
