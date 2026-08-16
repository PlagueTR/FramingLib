# Saving and Loading the Config

You can define the code that runs when the user clicks **Save** using the `setSavingRunnable()` method on the config screen builder.

```java title="java"
configBuilder.setSavingRunnable(() -> {
    example_mod.saveConfig();
});
```

- **Saving Runnable**: A callback method that triggers immediately when the user confirms (saves) the changes on the layout config screen. Use this block to run your mod's file serialization methods.

## Serializing and Deserializing Data

While FramingLib provides configuration UI utilities, it remains completely format-agnostic and does not lock you into a single config format.

Because the library uses factory creation patterns (like `AlignmentSizeOffset.create()`), standard reflective serialization doesn't work. You must handle serializing and deserializing these objects yourself.

The example below uses Google's GSON library, which is the preferred approach for many projects, including mine. However, this is just an example, and you are entirely free to serialize the data into TOML, YAML, or any other format you prefer.

## Saving and Loading Alignments/Alignment Size Offset with GSON

### Configuration Class

To handle your layout data with GSON, you must first define a config class for your mod's configuration.

Declare the values you want to store as instance fields. You must provide the fields with default values, or include a no-argument default constructor for this class to define and initialize the default values when no configuration file exists on the disk yet (first launch, or if the file is deleted, or if there's an error loading the file). You can optionally include getter and setter methods to access the fields.

```java title="java"
public class ModConfig {
    // The alignment size offset object we will store in our config
    private AlignmentSizeOffset layoutAlignmentSizeOffset;

    // Default constructor to initialize our config with default values
    public ModConfig() {
        this.layoutAlignmentSizeOffset = AlignmentSizeOffset.create(
            40,
            100,
            16,
            16,
            Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.TOP),
            Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP)
        );
    }

    // Getter for the alignment size offset
    public AlignmentSizeOffset getAlignmentSizeOffset() {
        return this.layoutAlignmentSizeOffset;
    }
}
```

### Custom GSON Type Adapter Implementation

To handle your layout data with GSON, you must create a custom type adapter that implements both `JsonSerializer<AlignmentSizeOffset>` and `JsonDeserializer<AlignmentSizeOffset>`.

When saving, extract properties directly from your persistent `AlignmentSizeOffset` object.  
When loading, read the fields from the JSON structure, and pass them back through the `create()` method to re-initialize your persistent `AlignmentSizeOffset` object.

```java title="java"
public class AlignmentSizeOffsetAdapter implements
JsonSerializer<AlignmentSizeOffset>, JsonDeserializer<AlignmentSizeOffset> {

    @Override
    public AlignmentSizeOffset deserialize(
        JsonElement json, Type typeOfT,
        JsonDeserializationContext context)
        throws JsonParseException {
        
            JsonObject obj = json.getAsJsonObject();

            int offsetX = obj.get("offset_x").getAsInt();
            int offsetY = obj.get("offset_y").getAsInt();
            int width = obj.get("width").getAsInt();
            int height = obj.get("height").getAsInt();

            JsonObject alignmentObj = obj.getAsJsonObject("alignment");
            Alignments.HAlignment hAlign =
                Alignments.HAlignment.valueOf(alignmentObj.get("h_alignment").getAsString());
            Alignments.VAlignment vAlign =
                Alignments.VAlignment.valueOf(alignmentObj.get("v_alignment").getAsString());

            JsonObject screenAlignmentObject = obj.getAsJsonObject("screen_alignment");
            Alignments.HAlignment screenHAlign =
                Alignments.HAlignment.valueOf(screenAlignmentObject.get("h_alignment").getAsString());
            Alignments.VAlignment screenVAlign =
                Alignments.VAlignment.valueOf(screenAlignmentObject.get("v_alignment").getAsString());

            return AlignmentSizeOffset.create(
                offsetX, offsetY,
                width, height,
                Alignments.create(hAlign, vAlign),
                Alignments.create(screenHAlign, screenVAlign)
            );
    }

    @Override
    public JsonElement serialize(
        AlignmentSizeOffset src, Type typeOfSrc,
        JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            json.addProperty("offset_x", src.getOffsetX());
            json.addProperty("offset_y", src.getOffsetY());
            json.addProperty("width", src.getWidth());
            json.addProperty("height", src.getHeight());

            JsonObject jsonAlign = new JsonObject();
            Alignments alignment = src.getAlignment();
            jsonAlign.addProperty("h_alignment", alignment.getHAlignment().name());
            jsonAlign.addProperty("v_alignment", alignment.getVAlignment().name());

            json.add("alignment", jsonAlign);

            JsonObject jsonScreenAlignment = new JsonObject();
            Alignments screenAlignment = src.getScreenAlignment();
            jsonScreenAlignment.addProperty("h_alignment", screenAlignment.getHAlignment().name());
            jsonScreenAlignment.addProperty("v_alignment", screenAlignment.getVAlignment().name());

            json.add("screen_alignment", jsonScreenAlignment);

            return json;
    }
}
```

### Integrating with a GSON Configuration Manager

Register your custom type adapter inside your config manager.

```java title="java" hl_lines="7"
public class ModConfigManager {

    private static File configFile;
    private static ModConfig config;
    private static ModConfig defaultConfig;
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(AlignmentSizeOffset.class, new AlignmentSizeOffsetAdapter())
            .create();
    
    private static void prepareConfigFile() {
        if (ModConfigManager.configFile != null) {
            return;
        }
        ModConfigManager.configFile = new File("config", "example_mod.json");
    }

    public static void initializeConfig() {
        if (ModConfigManager.config != null) {
            return;
        }
        ModConfigManager.config = new ModConfig();
        ModConfigManager.defaultConfig = new ModConfig();
        load();
    }

    public static void save() {
        prepareConfigFile();
        final String jsonString = gson.toJson(ModConfigManager.config);
        try {
            final FileWriter fileWriter = new FileWriter(ModConfigManager.configFile);
            try {
                fileWriter.write(jsonString);
                fileWriter.close();
            }
            catch (Throwable t) {
                try {
                    fileWriter.close();
                }
                catch (Throwable exception) {
                    t.addSuppressed(exception);
                }
                throw t;
            }
        }
        catch (IOException e) {
            Main.LOGGER.warn("[Example Mod] Couldn't save config file!", e);
        }
    }

    private static void load() {
        prepareConfigFile();
        try {
            if (!ModConfigManager.configFile.exists()) {
                save();
            }
            else{
                final BufferedReader br = new BufferedReader(new FileReader(ModConfigManager.configFile));
                final ModConfig parsed = gson.fromJson(br, ModConfig.class);
                if (parsed != null) {
                    ModConfigManager.config = parsed;
                }
            }
        }
        catch (FileNotFoundException e) {
            Main.LOGGER.warn("[Example Mod] Couldn't load config file!", e);
        }
    }

    public static ModConfig getConfig() {
        if (ModConfigManager.config == null) {
            ModConfigManager.config = new ModConfig();
        }
        return ModConfigManager.config;
    }

    public static ModConfig getDefault() {
        if (ModConfigManager.defaultConfig == null) {
            ModConfigManager.defaultConfig = new ModConfig();
        }
        return ModConfigManager.defaultConfig;
    }
}
```

### Using the Configuration Manager

Now, when your mod initializes, you can simply load your config using `ModConfigManager.initializeConfig()`. You can pass `ModConfigManager.save()` to your layout config screen builder to save the changes to a `.json` file when the user clicks **Save**.

You can also use the `ModConfigManager.getConfig()` or `ModConfigManager.getDefault()` methods throughout your mod to gain quick access to either the current config, or the default fallback config.
