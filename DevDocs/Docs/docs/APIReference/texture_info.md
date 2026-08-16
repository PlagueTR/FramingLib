# Texture Info

**Added**: `1.0.0`

An interface representing textures. It is designed to make it easier to draw textures anywhere on the screen.

Use the static factory method [create()](#create) to instantiate a new definition.

---

## Methods

| Return Type                | Signature                                                                               |
|:---------------------------|:----------------------------------------------------------------------------------------|
| `TextureInfo`              | [static create(@NotNull ResourceLocation texture, @Nullable Rectangle region)](#create) |
| `ResourceLocation`         | [getTexture()](#gettexture)                                                             |
| `int`                      | [getAtlasWidth()](#getatlaswidth)                                                       |
| `int`                      | [getAtlasHeight()](#getatlasheight)                                                     |
| `int`                      | [getWidth()](#getwidth)                                                                 |
| `int`                      | [getHeight()](#getheight)                                                               |
| [TextureUV](texture_uv.md) | [getUV()](#getuv)                                                                       |
| `boolean`                  | [isPixelSolid(int x, int y)](#ispixelsolid)                                             |
| `void`                     | [render(PoseStack poseStack, int x, int y, Color color)](#render)                       |

---

### create

**Added**: `1.0.0`

```java
static TextureInfo create(@NotNull ResourceLocation texture, @Nullable Rectangle region)
```

Static factory method that instantiates a new instance pointing to a resource location and a region for cropping a sub-texture.

- **Texture**: The resource location to the texture file.
- **Region**: The region defining the cropped sub-texture. Pass `null` to use the whole texture.

---

### getTexture

**Added**: `1.0.0`

```java
ResourceLocation getTexture()
```

Returns the `ResourceLocation` identifier pointing to the texture file.

---

### getAtlasWidth

**Added**: `1.0.0`

```java
int getAtlasWidth()
```

Returns the width of the entire texture.

---

### getAtlasHeight

**Added**: `1.0.0`

```java
int getAtlasHeight()
```

Returns the height of the entire texture.

---

### getWidth

**Added**: `1.0.0`

```java
int getWidth()
```

Returns the width of the cropped sub-texture. Same as [getAtlasWidth()](#getatlaswidth) if region was `null`.

---

### getHeight

**Added**: `1.0.0`

```java
int getHeight()
```

Returns the height of the cropped sub-texture. Same as [getAtlasHeight()](#getatlasheight) if region was `null`.

---

### getUV

**Added**: `1.0.0`

```java
TextureUV getUV()
```

Returns the [TextureUV](texture_uv.md) object that is used by the rendering methods.

---

### isPixelSolid

**Added**: `1.0.0`

```java
boolean isPixelSolid(int x, int y)
```

Returns `true` if the pixel at the given local coordinates is solid (opaque).

> The first time `isPixelSolid()` is called, it calculates and caches a bit mask to speed up consecutive checks.

---

### render

**Added**: `1.0.0`

```java
void render(PoseStack poseStack, int x, int y, Color color)
```

Renders the texture onto the screen at the specified coordinates.

- **poseStack**: The pose stack.
- **x**: The GUI scaled screen X coordinate to be used as the left-most coordinate.
- **y**: The GUI scaled screen Y coordinate to be used as the top-most coordinate.
- **color**: The [Color](color.md) to tint the texture. Pass white for normal rendering.
