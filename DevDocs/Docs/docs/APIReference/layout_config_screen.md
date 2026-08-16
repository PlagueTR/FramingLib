# Layout Config Screen

**Added**: `1.0.0`

An interface representing a layout config screen.

While it is mostly for internal use by FramingLib (and implemented by `FramingLayoutConfigScreen`), you can use it to interact with the current screen state.

For example, you can query whether the layout is edited via [isEdited()](#isedited) inside an `afterInitConsumer` callback to change UI logic dynamically.

---

## Methods

| Return Type                              | Signature                                                                                    |
|:-----------------------------------------|:---------------------------------------------------------------------------------------------|
| void                                     | [setSavingRunnable(@Nullable Runnable runnable)](#setsavingrunnable)                         |
| void                                     | [setAfterInitConsumer(@Nullable Consumer<Screen\> afterInitConsumer)](#setafterinitconsumer) |
| ResourceLocation                         | [getBackgroundTexture()](#getbackgroundtexture)                                              |
| List<[LayoutElement](layout_element.md)> | [getLayoutElementList()](#getlayoutelementlist)                                              |
| boolean                                  | [isEdited()](#isedited)                                                                      |
| boolean                                  | [isNotDefault()](#isnotdefault)                                                              |
| void                                     | [saveAll(boolean openOtherScreens)](#saveall)                                                |
| void                                     | [resetAll()](#resetall)                                                                      |
| void                                     | [quit()](#quit)                                                                              |
| void                                     | [setTooltip(Component tooltip)](#settooltip)                                                 |

---

### setSavingRunnable

**Added**: `1.0.0`

```java
void setSavingRunnable(@Nullable Runnable savingRunnable)
```

Sets the callback action that executes immediately when the **Save** button is pressed.  
Pass your serialization code.  
Pass `null` to remove the runnable.

`null` by default.

---

### setAfterInitConsumer

**Added**: `1.0.0`

```java
void setAfterInitConsumer(@Nullable Consumer<Screen> afterInitConsumer)
```

Sets the callback action that executes immediately after the screen finishes initialization. Provides the screen instance as a parameter.  
Pass `null` to remove the consumer.

`null` by default.

> **⚠️️** Certain actions such as resizing the window will reinitialize the screen, so the after init consumer may run multiple times on a screen

---

### getBackgroundTexture

**Added**: `1.0.0`

```java
ResourceLocation getBackgroundTexture()
```

Returns the `ResourceLocation` identifier pointing to the texture file used as the screen's rendering background pattern.

The dirt background is used by default.

---

### getLayoutElementList

**Added**: `1.0.0`

```java
List<LayoutElement> getLayoutElementList()
```

Returns a list containing all [LayoutElement](layout_element.md) instances currently registered on this config screen.

---

### isEdited

**Added**: `1.0.0`

```java
boolean isEdited()
```

Returns `true` if any [LayoutElement](layout_element.md)'s values are different from when the screen was initialized.

---

### isNotDefault

**Added**: `1.0.0`

```java
boolean isNotDefault()
```

Returns `true` if any [LayoutElement](layout_element.md)'s values are different from their default values. Requires [LayoutElement](layout_element.md)s to have a set default value.

---

### saveAll

**Added**: `1.0.0`

```java
void saveAll(boolean openOtherScreens)
```

Saves the value of every [LayoutElement](layout_element.md) instance, then executes the saving runnable.  
If `openOtherScreens` is set to `true`, closes the screen and opens the parent screen afterwards.

---

### resetAll

**Added**: `1.0.0`

```java
void resetAll()
```

Resets every [LayoutElement](layout_element.md)s' value back to their default values. Requires [LayoutElement](layout_element.md)s to have a set default value.

---

### quit

**Added**: `1.0.0`

```java
void quit()
```

Closes the screen and opens the parent screen.

---

### setTooltip

**Added**: `1.0.0`

```java
void setTooltip(Component tooltip)
```

Updates the active tooltip component displayed when cursor tracking interacts.

> **⚠️️** The tooltip gets reset every frame
