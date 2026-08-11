# Creating a Layout Element

You can easily create and add layout elements to your layout config screen using the layout element builder.

Before creating an element, you must

## Initialize the Builder

Instantiate a new `LayoutElementBuilder` by calling the `startLayoutElement()` method.

You must provide an `AlignmentSizeOffset` as the value, and a name for the element as parameters.

```java
LayoutElementBuilder layoutElementBuilder = configBuilder.startLayoutElement(
    example_mod.getConfig().getElementAlignmentSizeOffset(),
    new TranslatableComponent("text.example_mod.layout_element")
)
```

- You can store `AlignmentSizeOffset` directly in your config, or build it from your config using

You can then chain methods together to define the element's properties.  
For example

```java
layoutElementBuilder
    .setDefaultAlignmentSizeOffset(
        example_mod.getConfig().getDefaultElementAlignmentSizeOffset()
    )
    .setShowName(false);
```

To build and add the element, use the `addLayoutElementEntry()` method

```java
    configBuilder.addLayoutElementEntry(layoutElementBuilder.build());
```
