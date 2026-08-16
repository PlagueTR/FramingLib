# Alignments

**Added**: `1.0.0`

An interface representing horizontal and vertical alignment anchors used for positioning UI text, icons, buttons, and layouts.

Use the static factory method [create()](#create) to instantiate a combined alignment definition.

---

## Nested Types

### Alignments.HAlignment

Defines horizontal boundary positions:

* `LEFT`
* `MIDDLE`
* `RIGHT`

### Alignments.VAlignment

Defines vertical boundary positions:

* `TOP`
* `CENTER`
* `BOTTOM`

---

## Methods

| Return Type                         | Signature                                                              |
|:------------------------------------|:-----------------------------------------------------------------------|
| `Alignments`                        | [static create(HAlignment hAlignment, VAlignment vAlignment)](#create) |
| [HAlignment](#alignmentshalignment) | [getHAlignment()](#gethalignment)                                      |
| [VAlignment](#alignmentsvalignment) | [getVAlignment()](#getvalignment)                                      |
| `Alignments`                        | [copy()](#copy)                                                        |

---

### create

**Added**: `1.0.0`

```java
static Alignments create(HAlignment hAlignment, VAlignment vAlignment)
```

Static factory method that instantiates and returns a new instance of `Alignments`.

---

### getHAlignment

**Added**: `1.0.0`

```java
HAlignment getHAlignment()
```

Returns the horizontal alignment property enum value.

---

### getVAlignment

**Added**: `1.0.0`

```java
VAlignment getVAlignment()
```

Returns the vertical alignment property enum value.

---

### copy

**Added**: `1.0.0`

```java
Alignments copy()
```

Creates and returns a separate copy instance.
