# Layout Element Builder

**Added**: `1.0.0`

An interface used to configure and build instances of layout elements.

Use the [startLayoutElement()](layout_config_screen_builder.md#startlayoutelement) method inside a [LayoutConfigScreenBuilder](layout_config_screen_builder.md) to instantiate a new builder, chain your configuration settings, and finalize the creation by calling [build()](#build). Add the resulting [LayoutElement](layout_element.md) to your [LayoutConfigScreenBuilder](layout_config_screen_builder.md) by using [addLayoutElementEntry()](layout_config_screen_builder.md#addlayoutelemententry).

---

## Methods

| Return Type                        | Signature                                                                                                       |
|:-----------------------------------|:----------------------------------------------------------------------------------------------------------------|
| `LayoutElementBuilder`             | [setDefaultValue(Supplier<AlignmentSizeOffset\> defaultValue)](#setdefaultvalue)                                |
| `LayoutElementBuilder`             | [setDefaultValue(AlignmentSizeOffset defaultValue)](#setdefaultvalue)                                           |
| `LayoutElementBuilder`             | [setShowName(boolean showName)](#setshowname)                                                                   |
| `LayoutElementBuilder`             | [setNameAlignment(@NotNull Alignments nameAlignment)](#setnamealignment)                                        |
| `LayoutElementBuilder`             | [setNameAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)](#setnamealignment)       |
| `LayoutElementBuilder`             | [setShowIcon(boolean showIcon)](#setshowicon)                                                                   |
| `LayoutElementBuilder`             | [setIcon(TextureInfo textureInfo)](#seticon)                                                                    |
| `LayoutElementBuilder`             | [setIconAlignment(@NotNull Alignments iconAlignment)](#seticonalignment)                                        |
| `LayoutElementBuilder`             | [setIconAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)](#seticonalignment)       |
| `LayoutElementBuilder`             | [setColor(Color color)](#setcolor)                                                                              |
| `LayoutElementBuilder`             | [setDoesDrawBackground(boolean doesDrawBackground)](#setdoesdrawbackground)                                     |
| `LayoutElementBuilder`             | [setCustomRenderingFunction(BiConsumer<PoseStack, AlignmentSizeOffset\> function)](#setcustomrenderingfunction) |
| `LayoutElementBuilder`             | [setSnapping(boolean snapping)](#setsnapping)                                                                   |
| `LayoutElementBuilder`             | [setShowButtons(boolean showButtons)](#setshowbuttons)                                                          |
| `LayoutElementBuilder`             | [setButtonsAlignment(@NotNull Alignments buttonsAlignment)](#setbuttonsalignment)                               |
| `LayoutElementBuilder`             | [setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)](#setbuttonsalignment) |
| `LayoutElementBuilder`             | [setEnableResetButton(boolean enableResetButton)](#setenableresetbutton)                                        |
| [LayoutElement](layout_element.md) | [build()](#build)                                                                                               |

---

### setDefaultValue

**Added**: `1.0.0`

```java
LayoutElementBuilder setDefaultValue(Supplier<AlignmentSizeOffset> defaultValue)
LayoutElementBuilder setDefaultValue(AlignmentSizeOffset defaultValue)
```

Sets the default layout data of the layout element.  
Returns the instance of `LayoutElementBuilder`.

`null` by default.

> **⚠️️** Required for the ability to reset a layout element's value to its default value

---

### setShowName

**Added**: `1.0.0`

```java
LayoutElementBuilder setShowName(boolean showName)
```

Sets whether the element's name should be rendered on the screen.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

---

### setNameAlignment

**Added**: `1.0.0`

```java
LayoutElementBuilder setNameAlignment(@NotNull Alignments nameAlignment)
LayoutElementBuilder setNameAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)
```

Sets where the element's name is to be anchored within the element.  
Returns the instance of `LayoutElementBuilder`.

`Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP)` by default.

---

### setShowIcon

**Added**: `1.0.0`

```java
LayoutElementBuilder setShowIcon(boolean showIcon)
```

Sets whether the element's texture icon should be rendered on the screen.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

> **⚠️️** An icon should be set for it to render

---

### setIcon

**Added**: `1.0.0`

```java
LayoutElementBuilder setIcon(TextureInfo textureInfo)
```

Assigns the specific texture data to be drawn as the element's icon.  
Returns the instance of `LayoutElementBuilder`.

`null` by default.

---

### setIconAlignment

**Added**: `1.0.0`

```java
LayoutElementBuilder setIconAlignment(@NotNull Alignments iconAlignment)
LayoutElementBuilder setIconAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)
```

Sets where the icon is to be anchored within the element.  
Returns the instance of `LayoutElementBuilder`.

`Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP)` by default.

---

### setColor

**Added**: `1.0.0`

```java
LayoutElementBuilder setColor(Color color)
```

Sets the tint of the elements default background and it's buttons.  
Returns the instance of `LayoutElementBuilder`.

`Color.create(196, 196, 196)` by default.

---

### setDoesDrawBackground

**Added**: `1.0.0`

```java
LayoutElementBuilder setDoesDrawBackground(boolean doesDrawBackground)
```

Sets whether the default background should be drawn behind the element.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

---

### setCustomRenderingFunction

**Added**: `1.0.0`

```java
LayoutElementBuilder setCustomRenderingFunction(BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction)
```

Registers a custom drawing function code to supplement how this layout component renders. Provides the pose stack and layout data as parameters.  
Returns the instance of `LayoutElementBuilder`.

`null` by default.

---

### setSnapping

**Added**: `1.0.0`

```java
LayoutElementBuilder setSnapping(boolean snapping)
```

Determines if the element snap to the center of the screen.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

---

### setShowButtons

**Added**: `1.0.0`

```java
LayoutElementBuilder setShowButtons(boolean showButtons)
```

Sets whether the buttons are to be rendered for the element.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

> **⚠️️** As of version 1.0.0, there is only a **Reset** button for elements

---

### setButtonsAlignment

**Added**: `1.0.0`

```java
LayoutElementBuilder setButtonsAlignment(@NotNull Alignments buttonsAlignment)
LayoutElementBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)
```

Sets where the element's buttons are to be anchored within the element.  
Returns the instance of `LayoutElementBuilder`.

`Alignments.create(Alignments.HAlignment.RIGHT, Alignments.VAlignment.TOP)` by default.

---

### setEnableResetButton

**Added**: `1.0.0`

```java
LayoutElementBuilder setEnableResetButton(boolean enableResetButton)
```

Sets whether the **Reset** button is to be rendered on screen.  
Returns the instance of `LayoutElementBuilder`.

`true` by default.

---

### build

**Added**: `1.0.0`

```java
LayoutElement build()
```

Compiles all the properties into a finalized, fully functional [LayoutElement](layout_element.md) instance and returns it.
