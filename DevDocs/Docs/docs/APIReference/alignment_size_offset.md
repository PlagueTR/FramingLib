# Alignment Size Offset

**Added**: `1.0.0`

An interface representing layout data for a UI element. It manages sizes, offsets, screen anchors, coordinate translations, and state flags.

Use the static factory methods [create()](#create) or [fromActualPosition()](#fromactualposition) to instantiate new definitions.

---

## Methods

| Return Type                 | Signature                                                                                                                                     |
|:----------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------|
| `AlignmentSizeOffset`       | [static create(int offsetX, int offsetY, int width, int height, @NotNull Alignments alignment, @NotNull Alignments screenAlignment)](#create) |
| `AlignmentSizeOffset`       | [static fromActualPosition(int actualX, int actualY, int width, int height, @NotNull Alignments screenAlignment)](#fromactualposition)        |
| `int`                       | [getOffsetX()](#getoffsetx)                                                                                                                   |
| `int`                       | [getOffsetY()](#getoffsety)                                                                                                                   |
| `void`                      | [setOffsetX(int offsetX)](#setoffsetx)                                                                                                        |
| `void`                      | [setOffsetY(int offsetY)](#setoffsety)                                                                                                        |
| `int`                       | [getActualX()](#getactualx)                                                                                                                   |
| `int`                       | [getActualY()](#getactualy)                                                                                                                   |
| `int`                       | [getWidth()](#getwidth)                                                                                                                       |
| `int`                       | [getHeight()](#getheight)                                                                                                                     |
| `void`                      | [setWidth(int width)](#setwidth)                                                                                                              |
| `void`                      | [setHeight(int height)](#setheight)                                                                                                           |
| [Alignments](alignments.md) | [getAlignment()](#getalignment)                                                                                                               |
| `void`                      | [setAlignment(@NotNull Alignments alignment)](#setalignment)                                                                                  |
| [Alignments](alignments.md) | [getScreenAlignment()](#getscreenalignment)                                                                                                   |
| `void`                      | [setScreenAlignment(@NotNull Alignments screenAlignment)](#setscreenalignment)                                                                |
| `void`                      | [markDirty()](#markdirty)                                                                                                                     |
| `boolean`                   | [isDirty()](#isdirty)                                                                                                                         |
| `void`                      | [setIsEditing(boolean editing)](#setisediting)                                                                                                |
| `boolean`                   | [isEditing()](#isediting)                                                                                                                     |
| `boolean`                   | [hasUnsavedChanges()](#hasunsavedchanges)                                                                                                     |
| `boolean`                   | [isSimilar(AlignmentSizeOffset other)](#issimilar)                                                                                            |
| `AlignmentSizeOffset`       | [copy()](#copy)                                                                                                                               |

---

### create

**Added**: `1.0.0`

```java
static AlignmentSizeOffset create(
    int offsetX, int offsetY,
    int width, int height,
    @NotNull Alignments alignment,
    @NotNull Alignments screenAlignment)
```

Static factory method that instantiates a new instance using offsets, dimensions, alignment anchors, and screen alignment anchors.

- **Offset X**: Relative position offset of the element in the X axis.
- **Offset Y**: Relative position offset of the element in the Y axis.
- **Width**: Width of the element.
- **Height**: Height of the element.
- **Alignment**: Anchor point of the element.
- **Screen Alignment**: The origin point on the screen used to calculate the returned coordinates when using `getActualX()`/`getActualY()` methods.

---

### fromActualPosition

**Added**: `1.0.0`

```java
static AlignmentSizeOffset fromActualPosition(int actualX, int actualY, int width, int height, @NotNull Alignments screenAlignment)
```

Static factory method that instantiates a new instance translating GUI scaled screen-space coordinates into alignment data based on a provided screen alignment anchor and dimensions.

- **Actual X**: GUI scaled screen space X coordinate of the element relative to screen alignment.
- **Actual Y**: GUI scaled screen space Y coordinate of the element relative to screen alignment.
- **Width**: Width of the element.
- **Height**: Height of the element.
- **Screen Alignment**: The origin point on the screen used to calculate the returned coordinates when using `getActualX()`/`getActualY()` methods.

---

### getOffsetX

**Added**: `1.0.0`

```java
int getOffsetX()
```

Returns the relative horizontal coordinate displacement offset from its anchoring point. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value returned also depends on whether the object is in edit state or not

---

### getOffsetY

**Added**: `1.0.0`

```java
int getOffsetY()
```

Returns the relative vertical coordinate displacement offset from its anchoring point. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value returned also depends on whether the object is in edit state or not

---

### setOffsetX

**Added**: `1.0.0`

```java
void setOffsetX(int offsetX)
```

Sets the relative horizontal coordinate displacement offset. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value to be set also depends on whether the object is in edit state or not

---

### setOffsetY

**Added**: `1.0.0`

```java
void setOffsetY(int offsetY)
```

Sets the relative vertical coordinate displacement offset. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value to be set also depends on whether the object is in edit state or not

---

### getActualX

**Added**: `1.0.0`

```java
int getActualX()
```

Calculates, caches and returns the GUI scaled screen space X coordinate calculated from sizes and alignment anchor. The returned coordinate is relative to screen alignment.

> **⚠️️** The value returned also depends on whether the object is in edit state or not

---

### getActualY

**Added**: `1.0.0`

```java
int getActualY()
```

Calculates, caches and returns the GUI scaled screen space Y coordinate calculated from sizes and alignment anchor. The returned coordinate is relative to screen alignment.

> **⚠️️** The value returned also depends on whether the object is in edit state or not

---

### getWidth

**Added**: `1.0.0`

```java
int getWidth()
```

Returns the width of the element.

---

### getHeight

**Added**: `1.0.0`

```java
int getHeight()
```

Returns the height of the element.

---

### setWidth

**Added**: `1.0.0`

```java
void setWidth(int width)
```

Sets the width of the element.

---

### setHeight

**Added**: `1.0.0`

```java
void setHeight(int height)
```

Sets the height of the element.

---

### getAlignment

**Added**: `1.0.0`

```java
Alignments getAlignment()
```

Returns the alignment anchor. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value returned also depends on whether the object is in edit state or not

---

### setAlignment

**Added**: `1.0.0`

```java
void setAlignment(@NotNull Alignments alignment)
```

Sets the alignment anchor. It is updated automatically if the layout element is moved by the user.

> **⚠️️** The value to be set also depends on whether the object is in edit state or not

---

### getScreenAlignment

**Added**: `1.0.0`

```java
Alignments getScreenAlignment()
```

Returns the screen alignment anchor. This alters the coordinates returned by [getActualX()](#getactualx) and [getActualY()](#getactualy) methods.

---

### setScreenAlignment

**Added**: `1.0.0`

```java
void setScreenAlignment(@NotNull Alignments screenAlignment)
```

Sets the screen alignment anchor. This alters the coordinates returned by [getActualX()](#getactualx) and [getActualY()](#getactualy) methods.

---

### markDirty

**Added**: `1.0.0`

```java
void markDirty()
```

Flags the layout state as dirty. The next time [getActualX()](#getactualx) or [getActualY()](#getactualy) is called, the cached value will be recalculated and re-cached.  
It is automatically marked as dirty if the layout element has been moved by the user, the GUI scale has been changed, or the game window has been resized.

---

### isDirty

**Added**: `1.0.0`

```java
boolean isDirty()
```

Returns `true` if layout state is marked as dirty. The next time [getActualX()](#getactualx) or [getActualY()](#getactualy) is called, the cached value will be recalculated and re-cached.  
It is automatically marked as dirty if the layout element has been moved by the user, the GUI scale has changed, or the game window has been resized.

---

### setIsEditing

**Added**: `1.0.0`

```java
void setIsEditing(boolean editing)
```

Sets the element's edit state. This is used to store changes without overriding the previous values if the user decides to cancel or discard their changes.  
It is automatically set when a config screen containing this element is opened or closed.

---

### isEditing

**Added**: `1.0.0`

```java
boolean isEditing()
```

Returns `true` if the element is in edit state. This is used to store changes without overriding the previous values if the user decides to cancel or discard their changes.  
It is automatically set when a config screen containing this element is opened or closed.

---

### hasUnsavedChanges

**Added**: `1.0.0`

```java
boolean hasUnsavedChanges()
```

Returns `true` if its edit state values differ from its saved values.

---

### isSimilar

**Added**: `1.0.0`

```java
boolean isSimilar(AlignmentSizeOffset other)
```

Compares offset, size and alignment properties against another `AlignmentSizeOffset` object to evaluate similarity.
Returns `true` if all the properties are the same.

> **⚠️️** This does not mean that the two `AlignmentSizeOffset` objects have the same values.
> It means `getOffsetX()`, `getOffsetY()`, `getWidth()`, `getHeight()` and `getAlignment()` report the same values.
> The values returned by those functions can change when the object is in edit state.

### copy

**Added**: `1.0.0`

```java
AlignmentSizeOffset copy()
```

Creates and returns a separate copy instance.
