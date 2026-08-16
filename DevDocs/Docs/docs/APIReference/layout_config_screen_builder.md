# Layout Config Screen Builder

**Added**: `1.0.0`

An interface used to configure and build instances of a layout configuration screen.

Use the static factory method [create()](#create) to instantiate a new builder, chain your configuration settings, and finalize the creation by calling [build()](#build).

---

## Methods

| Return Type                                       | Signature                                                                                                       |
|:--------------------------------------------------|:----------------------------------------------------------------------------------------------------------------|
| `LayoutConfigScreenBuilder`                       | [static create()](#create)                                                                                      |
| `Component`                                       | [getTitle()](#gettitle)                                                                                         |
| `LayoutConfigScreenBuilder`                       | [setTitle(Component title)](#settitle)                                                                          |
| `Screen`                                          | [getParentScreen()](#getparentscreen)                                                                           |
| `LayoutConfigScreenBuilder`                       | [setParentScreen(Screen parentScreen)](#setparentscreen)                                                        |
| `Runnable`                                        | [getSavingRunnable()](#getsavingrunnable)                                                                       |
| `LayoutConfigScreenBuilder`                       | [setSavingRunnable(Runnable savingRunnable)](#setsavingrunnable)                                                |
| `boolean`                                         | [doesShowButtons()](#doesshowbuttons)                                                                           |
| `LayoutConfigScreenBuilder`                       | [setDoesShowButtons(boolean doesShowButtons)](#setdoesshowbuttons)                                              |
| `Alignments`                                      | [getButtonsAlignment()](#getbuttonsalignment)                                                                   |
| `LayoutConfigScreenBuilder`                       | [setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)](#setbuttonsalignment) |
| `LayoutConfigScreenBuilder`                       | [setButtonsAlignment(Alignments buttonsAlignment)](#setbuttonsalignment)                                        |
| `boolean`                                         | [doesShowResetButton()](#doesshowresetbutton)                                                                   |
| `LayoutConfigScreenBuilder`                       | [setShowResetButton(boolean showResetButton)](#setshowresetbutton)                                              |
| `Consumer<Screen>`                                | [getAfterInitConsumer()](#getafterinitconsumer)                                                                 |
| `LayoutConfigScreenBuilder`                       | [setAfterInitConsumer(Consumer<Screen\> afterInitConsumer)](#setafterinitconsumer)                              |
| `ResourceLocation`                                | [getBackgroundTexture()](#getbackgroundtexture)                                                                 |
| `LayoutConfigScreenBuilder`                       | [setBackgroundTexture(ResourceLocation backgroundTexture)](#setbackgroundtexture)                               |
| `boolean`                                         | [hasTransparentBackground()](#hastransparentbackground)                                                         |
| `LayoutConfigScreenBuilder`                       | [setTransparentBackground(boolean transparentBackground)](#settransparentbackground)                            |
| `LayoutConfigScreenBuilder`                       | [addLayoutElementEntry(LayoutElement elementEntry)](#addlayoutelemententry)                                     |
| [LayoutElementBuilder](layout_element_builder.md) | [startLayoutElement(@NotNull AlignmentSizeOffset alignmentSizeOffset, Component name)](#startlayoutelement)     |
| `Screen`                                          | [build()](#build)                                                                                               |

---

### create

**Added**: `1.0.0`

```java
static LayoutConfigScreenBuilder create()
```

Static factory method that instantiates and returns a new instance of `LayoutConfigScreenBuilder`.

---

### getTitle

**Added**: `1.0.0`

```java
Component getTitle()
```

Returns the text `Component` assigned as the screen's title.  
This is also the text to be used by the narrator.

`new TranslatableComponent("text.framinglib.config_title")` - `en_US: Layout Config` by default.

---

### setTitle

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setTitle(Component title)
```

Sets the title text `Component` of the screen.  
Returns the instance of `LayoutConfigScreenBuilder`.  
This is also the text to be used by the narrator.

`new TranslatableComponent("text.framinglib.config_title")` - `en_US: Layout Config` by default.

---

### getParentScreen

**Added**: `1.0.0`

```java
Screen getParentScreen()
```

Returns the parent `Screen` that opens when this layout configuration UI is exited.

`null` by default.

---

### setParentScreen

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setParentScreen(Screen parentScreen)
```

Sets the parent `Screen` which will be displayed automatically when the screen is exited.  
Returns the instance of `LayoutConfigScreenBuilder`.

`null` by default.

---

### getSavingRunnable

**Added**: `1.0.0`

```java
Runnable getSavingRunnable()
```

Returns the serialization `Runnable` to be executed when the **Save** button is pressed.

`null` by default.

---

### setSavingRunnable

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setSavingRunnable(Runnable savingRunnable)
```

Sets a callback action that executes immediately when the **Save** button is pressed.  
Returns the instance of `LayoutConfigScreenBuilder`.  
Pass your serialization code.  
Pass `null` to remove the runnable.

`null` by default.

---

### doesShowButtons

**Added**: `1.0.0`

```java
boolean doesShowButtons()
```

Returns `true` if **Save**, **Cancel**/**Discard**, **Reset All** buttons are to be rendered on screen.

`true` by default.

---

### setDoesShowButtons

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setDoesShowButtons(boolean doesShowButtons)
```

Sets whether **Save**, **Cancel**/**Discard**, **Reset All** buttons are to be rendered on screen.  
Returns the instance of `LayoutConfigScreenBuilder`.

`true` by default.

---

### getButtonsAlignment

**Added**: `1.0.0`

```java
Alignments getButtonsAlignment()
```

Returns where the **Save**, **Cancel**/**Discard**, **Reset All** buttons are to be anchored on screen.

`Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.TOP)` by default.

---

### setButtonsAlignment

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment)
LayoutConfigScreenBuilder setButtonsAlignment(Alignments buttonsAlignment)
```

Sets where the **Save**, **Cancel**/**Discard**, **Reset All** buttons are to be anchored on screen.  
Returns the instance of `LayoutConfigScreenBuilder`.

`Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.TOP)` by default.

---

### doesShowResetButton

**Added**: `1.0.0`

```java
boolean doesShowResetButton()
```

Returns `true` if the **Reset All** button is to be rendered on screen.

`true` by default.

---

### setShowResetButton

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setShowResetButton(boolean showResetButton)
```

Sets whether the **Reset All** button is to be rendered on screen.  
Returns the instance of `LayoutConfigScreenBuilder`.

`true` by default.

---

### getAfterInitConsumer

**Added**: `1.0.0`

```java
Consumer<Screen> getAfterInitConsumer()
```

Gets the callback action that executes immediately after the screen finishes initialization. Provides the screen instance as a parameter.

`null` by default.

> **⚠️️** Certain actions such as resizing the window will reinitialize the screen, so the after init consumer may run multiple times on a screen

---

### setAfterInitConsumer

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setAfterInitConsumer(Consumer<Screen> afterInitConsumer)
```

Sets the callback action that executes immediately after the screen finishes initialization. Provides the screen instance as a parameter.  
Returns the instance of `LayoutConfigScreenBuilder`.  
Pass `null` to remove the consumer.

`null` by default.

> **⚠️️** Certain actions such as resizing the window will reinitialize the screen, so the after init consumer may run multiple times on a screen

---

### getBackgroundTexture

**Added**: `1.0.0`

```java
ResourceLocation getBackgroundTexture()
```

Returns the `ResourceLocation` identifier pointing to the texture file to be used as the screen's rendering background pattern.

The dirt background texture is used by default.

---

### setBackgroundTexture

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setBackgroundTexture(ResourceLocation backgroundTexture)
```

Sets the `ResourceLocation` identifier pointing to the texture file to be used as the screen's rendering background pattern.  
Returns the instance of `LayoutConfigScreenBuilder`.

The dirt background texture is used by default.

---

### hasTransparentBackground

**Added**: `1.0.0`

```java
boolean hasTransparentBackground()
```

Returns `true` if the screen background is set to be transparent instead of rendering the background texture.

`false` by default.

> **⚠️️** The screen will be transparent only when the user is in-game

---

### setTransparentBackground

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder setTransparentBackground(boolean transparentBackground)
```

Sets whether the screen background is to be transparent instead of rendering the background texture.  
Returns the instance of `LayoutConfigScreenBuilder`.

`false` by default.

> **⚠️️** The screen will be transparent only when the user is in-game

---

### addLayoutElementEntry

**Added**: `1.0.0`

```java
LayoutConfigScreenBuilder addLayoutElementEntry(LayoutElement elementEntry)
```

Registers an existing [LayoutElement](layout_element.md) object into the screen.  
Returns the instance of `LayoutConfigScreenBuilder`.

---

### startLayoutElement

**Added**: `1.0.0`

```java
default LayoutElementBuilder startLayoutElement(
    @NotNull AlignmentSizeOffset alignmentSizeOffset,
    Component name)
```

Instantiates and returns a new instance of [LayoutElementBuilder](layout_element_builder.md).  
This is how you should build layout elements to be registered to the screen.

- **Alignment Size Offset**: The initial layout data (value) of the element - not to be confused with the default value.
- **Name**: The name of the element.

---

### build

**Added**: `1.0.0`

```java
Screen build()
```

Compiles all the properties into a finalized, fully functional `Screen` instance and returns it.
