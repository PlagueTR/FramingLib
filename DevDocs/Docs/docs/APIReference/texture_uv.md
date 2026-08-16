# Texture UV

**Added**: `1.0.0`

An interface representing the UV mapping of a texture. It maps texture coordinates normalized as floats from `0.0f` to `1.0f` to determine how textures are rendered onto shapes.

Use the static factory methods [createFromRegion()](#createfromregion) or [createFromUV()](#createfromuv) to instantiate a new definition.

---

## Methods

| Return Type | Signature                                                                                                 |
|:------------|:----------------------------------------------------------------------------------------------------------|
| `TextureUV` | [static createFromRegion(int atlasWidth, int atlasHeight, @Nullable Rectangle region)](#createfromregion) |
| `TextureUV` | [static createFromUV(float u_min, float u_max, float v_min, float v_max)](#createfromuv)                  |
| `float`     | [getUMin()](#getumin)                                                                                     |
| `float`     | [getUMax()](#getumax)                                                                                     |
| `float`     | [getVMin()](#getvmin)                                                                                     |
| `float`     | [getVMax()](#getvmax)                                                                                     |

---

### createFromRegion

**Added**: `1.0.0`

```java
static TextureUV createFromRegion(
    int atlasWidth, int atlasHeight,
    @Nullable Rectangle region)
```

Static factory method that instantiates a new instance by converting texture region into normalized floating-point UV coordinates.

- **Atlas Width**: The total width of the texture file in pixels.
- **Atlas Height**: The total height of the texture file in pixels.
- **Region**: The bounding box sub-region defining the pixel coordinates to map. Pass `null` to use the whole texture bounds (`0.0f` to `1.0f`).

---

### createFromUV

**Added**: `1.0.0`

```java
static TextureUV createFromUV(
    float u_min, float u_max,
    float v_min, float v_max)
```

Static factory method that instantiates a new instance using pre-calculated, raw normalized floating-point UV coordinates.

- **U Min**: The minimum horizontal coordinate (the left edge).
- **U Max**: The maximum horizontal coordinate (the right edge).
- **V Min**: The minimum vertical coordinate (the top edge).
- **V Max**: The maximum vertical coordinate (the bottom edge).

---

### getUMin

**Added**: `1.0.0`

```java
float getUMin()
```

Returns the minimum horizontal mapping coordinate value (the left edge).

---

### getUMax

**Added**: `1.0.0`

```java
float getUMax()
```

Returns the maximum horizontal mapping coordinate value (the right edge).

---

### getVMin

**Added**: `1.0.0`

```java
float getVMin()
```

Returns the minimum vertical mapping coordinate value (the top edge).

---

### getVMax

**Added**: `1.0.0`

```java
float getVMax()
```

Returns the maximum vertical mapping coordinate value (the bottom edge).
