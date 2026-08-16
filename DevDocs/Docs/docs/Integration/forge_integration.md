# Forge Integration

Sadly, Forge's API for registering your config screen changes depending on Forge/Minecraft versions.

The examples below target Minecraft/Forge 1.16.5.

## Registering Config Screen Factory

Register your config screen during your client-side setup via `registerExtensionPoint()` for `ConfigScreenHandler.ConfigScreenFactory.class`.

You should check to make sure `framinglib` is loaded.

```java title="java"
@Mod("example_mod")
public final class ExampleMod {

    public ExampleMod() {

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientMain.init(this);
        }
        else {
            ServerMain.init(this);
        }

    }

    private static class ClientMain() {
        private static void init(ExampleMod instance) {
            // Your client logic goes here

            if (ModList.get().isLoaded("framinglib")) {
                ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () ->
                    (minecraft, screen) -> instance.getLayoutConfigScreenBuilder().build()
                );
            }
        }
    }

    private static class ServerMain() {
        private static void init(ExampleMod instance) {
            // Your server logic goes here
        }
    }

    public LayoutConfigScreenBuilder getLayoutConfigScreenBuilder() {
        // Your layout config screen builder
    }

}
```
