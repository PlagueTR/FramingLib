# Handling Alignment Size Offset

Layout elements rely on an `AlignmentSizeOffset` object to determine where they should be placed on the screen.

The library updates this persistent object automatically when users make changes in the config screen, or when Minecraft window size, or GUI scale changes.

## Creating the Alignment Size Offset Object

Instantiate a new `AlignmentSizeOffset` object by calling the `create()` method.

```java title="java"
AlignmentSizeOffset elementAlignmentSizeOffset = AlignmentSizeOffset.create(
    offsetX,
    offsetY,
    width,
    height,
    alignment,
    screenAlignment
);
```

- **Offset X**: Relative position offset of the element in the X axis.
- **Offset Y**: Relative position offset of the element in the Y axis.
- **Width**: Width of the element.
- **Height**: Height of the element.
- **Alignment**: Anchor point of the element.
- **Screen Alignment**: The origin point on the screen used to calculate the returned coordinates when using `getActualX()`/`getActualY()` methods.

> **⚠️️** While you *can* split the data into individual fields inside your config file, for your mod to perform smoothly, you must construct the `AlignmentSizeOffset` object once (for example, when your mod loads) and reuse that single, persistent instance everywhere it's needed.

### Creating the Alignments Object

You can create an `Alignments` object using the `create()` method.  
For example:

```java title="java"
Alignments alignment = Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP);
```

### Complete Instantiation Example

```java title="java"
AlignmentSizeOffset elementAlignmentSizeOffset = AlignmentSizeOffset.create(
    40,
    100,
    16,
    16,
    Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.TOP),
    Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP)
);
```

Will create an `AlignmentSizeOffset` object that is 16x16 in size, is 40 pixels to the right of the center vertically, and 100 pixels below the top of the screen, but when `.getActualX()` or `.getActualY()` function is used, it will report its X and Y coordinates relative to top-left of the screen.

## Using the Alignment Size Offset Object

Once you have your persistent `AlignmentSizeOffset` object, you can utilize its `getActualX()` and `getActualY()` methods inside your HUD/overlay rendering loops.

FramingLib caches the calculations, and only updates them when needed, so don't be afraid of calling the methods every frame. In fact, that is the intended way to use the library.

### Custom Element Example

If you're drawing custom HUD elements/overlay, query `.getActualX()` and `.getActualY()` to find absolute drawing origin pixel relative to the top-left corner of the window.  
Pass these variables directly to your draw hooks:

```java title="java"
public void render(PoseStack poseStack) {
    AlignmentSizeOffset elementASO = example_mod.getConfigManager().getConfig().getAlignmentSizeOffset();

    int drawX = elementASO.getActualX();
    int drawY = elementASO.getActualY();

    // Render your custom element at the position at drawX, drawY
}
```

- **Note**: You can take advantage of the existing `TextureInfo.render()` method to draw your custom elements.

### Existing Element Example

If you want to move existing HUD elements, create a `Mixin` and apply a matrix translation directly using Mojang's `PoseStack`:

```java title="java"
@Mixin(SomeHUDElement.class)
public class MixinSomeHUDElement {

    @Inject(
        method = "render", at = @At("HEAD")
    )
    private void beforeRender(PoseStack poseStack, CallbackInfo ci) {
        AlignmentSizeOffset elementASO = example_mod.getConfigManager().getConfig().getAlignmentSizeOffset();

        poseStack.pushPose();
        poseStack.translate(
            elementASO.getActualX(),
            elementASO.getActualY(),
            0.0f
        );
    }

    @Inject(
        method = "render", at = @At("RETURN")
    )
    private void afterRender(PoseStack poseStack, CallbackInfo ci) {
        poseStack.popPose();
    }

}
```

You can learn more about `Alignments` at [Alignments API Reference Page](../APIReference/alignments.md)

You can learn more about `AlignmentSizeOffset` at [AlignmentSizeOffset API Reference Page](../APIReference/alignment_size_offset.md)

You can learn more about `TextureInfo` at [TextureInfo API Reference Page](../APIReference/texture_info.md)
