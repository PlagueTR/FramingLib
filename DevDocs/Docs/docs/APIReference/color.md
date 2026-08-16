# Color

**Added**: `1.0.0`

An interface representing RGBA color data. It supports creation via integer ranges, float ranges, or hexadecimal string inputs, and exposes color codes in multiple formats.

Use the static factory [create()](#create) methods to instantiate new definitions.

---

## Methods

| Return Type | Signature                                                                 |
|:------------|:--------------------------------------------------------------------------|
| `Color`     | [static create(int red, int green, int blue)](#create)                    |
| `Color`     | [static create(int red, int green, int blue, int alpha)](#create)         |
| `Color`     | [static create(float red, float green, float blue)](#create)              |
| `Color`     | [static create(float red, float green, float blue, float alpha)](#create) |
| `Color`     | [static create(String hex)](#create)                                      |
| `float`     | [getRedFloat()](#getredfloat)                                             |
| `float`     | [getGreenFloat()](#getgreenfloat)                                         |
| `float`     | [getBlueFloat()](#getbluefloat)                                           |
| `float`     | [getAlphaFloat()](#getalphafloat)                                         |
| `int`       | [getRedInt()](#getredint)                                                 |
| `int`       | [getGreenInt()](#getgreenint)                                             |
| `int`       | [getBlueInt()](#getblueint)                                               |
| `int`       | [getAlphaInt()](#getalphaint)                                             |
| `int`       | [getARGB()](#getargb)                                                     |
| `int`       | [getRGB()](#getrgb)                                                       |
| `Color`     | [copy()](#copy)                                                           |

---

### create

**Added**: `1.0.0`

```java
static Color create(int red, int green, int blue)
static Color create(int red, int green, int blue, int alpha)
static Color create(float red, float green, float blue)
static Color create(float red, float green, float blue, float alpha)
static Color create(String hex)
```

Static factory methods that instantiate a new instance of `Color`.

- **int channels**: Accepts standard color values from `0` to `255`. If alpha is omitted, it defaults to `255` (fully opaque).
- **float channels**: Accepts normalized decimal values from `0.0f` to `1.0f`. If alpha is omitted, it defaults to `1.0f` (fully opaque).
- **hex**: Parses a standard hexadecimal string representation (For example, `"#FF5555"`, `"FF5555"`, or with alpha channel variants).

---

### getRedFloat

**Added**: `1.0.0`

```java
float getRedFloat()
```

Returns the red channel value normalized as a float between `0.0f` and `1.0f`.

---

### getGreenFloat

**Added**: `1.0.0`

```java
float getGreenFloat()
```

Returns the green channel value normalized as a float between `0.0f` and `1.0f`.

---

### getBlueFloat

**Added**: `1.0.0`

```java
float getBlueFloat()
```

Returns the blue channel value normalized as a float between `0.0f` and `1.0f`.

---

### getAlphaFloat

**Added**: `1.0.0`

```java
float getAlphaFloat()
```

Returns the alpha opacity channel value normalized as a float between `0.0f` and `1.0f`.

---

### getRedInt

**Added**: `1.0.0`

```java
int getRedInt()
```

Returns the raw red channel value as an integer between `0` and `255`.

---

### getGreenInt

**Added**: `1.0.0`

```java
int getGreenInt()
```

Returns the raw green channel value as an integer between `0` and `255`.

---

### getBlueInt

**Added**: `1.0.0`

```java
int getBlueInt()
```

Returns the raw blue channel value as an integer between `0` and `255`.

---

### getAlphaInt

**Added**: `1.0.0`

```java
int getAlphaInt()
```

Returns the raw alpha opacity channel value as an integer between `0` and `255`.

---

### getARGB

**Added**: `1.0.0`

```java
int getARGB()
```

Returns an int that packs alpha, red, green and blue values together.  
This is the color format used by Minecraft's internal rendering methods.

---

### getRGB

**Added**: `1.0.0`

```java
int getRGB()
```

Returns an int that packs alpha, red, green and blue values together. Alpha is considered `1.0f` (fully opaque) in this case.

---

### copy

**Added**: `1.0.0`

```java
Color copy()
```

Creates and returns a separate copy instance.
