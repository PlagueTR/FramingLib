# Creating a Layout Element

You can easily create and add layout elements to your layout config screen using the layout element builder.

## Initialize the Builder

Instantiate a new `LayoutElementBuilder` by calling the `startLayoutElement()` method.

You must pass your persistent `AlignmentSizeOffset` object as the value, and a name for the element as parameters.

```java title="java"
LayoutElementBuilder layoutElementBuilder = configBuilder.startLayoutElement(
    example_mod.getConfigManager().getConfig().getAlignmentSizeOffset(),
    new TranslatableComponent("text.example_mod.layout_element")
);
```

You can then chain methods together to define the element's properties.  
For example

```java title="java"
layoutElementBuilder
    .setDefaultValue(
        example_mod.getConfigManager().getDefault().getAlignmentSizeOffset()
    )
    .setShowName(false);
```

- **Default Value**: The default `AlignmentSizeOffset` value used if a player hits the reset button on the config UI.
- **Show Name**: A boolean setting that toggles whether a text label renders for this element on the config screen.

## Building and Registering the Element

Build the layout element using `LayoutElementBuilder.build()` and register it to your `LayoutConfigScreenBuilder` instance by passing it into the `addLayoutElementEntry()` method.

```java title="java"
configBuilder.addLayoutElementEntry(layoutElementBuilder.build());
```

You can learn more about `LayoutElementBuilder` at [LayoutElementBuilder API Reference Page](../APIReference/layout_element_builder.md)
