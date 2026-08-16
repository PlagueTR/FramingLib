# Layout Element

**Added**: `1.0.0`

An interface representing a layout element registered to a configuration screen.

While these elements are managed automatically by the config screen, you can use this interface to track individual element states, check if values have changed, or manually trigger resets and saves.

---

## Methods

| Return Type | Signature                                                             |
|:------------|:----------------------------------------------------------------------|
| `boolean`   | [isCurrentlySnappingHorizontally()](#iscurrentlysnappinghorizontally) |
| `boolean`   | [isCurrentlySnappingVertically()](#iscurrentlysnappingvertically)     |
| `boolean`   | [isEdited()](#isedited)                                               |
| `boolean`   | [isNotDefault()](#isnotdefault)                                       |
| `void`      | [save()](#save)                                                       |
| `void`      | [resetValue()](#resetvalue)                                           |

---

### isCurrentlySnappingHorizontally

**Added**: `1.0.0`

```java
boolean isCurrentlySnappingHorizontally()
```

Returns `true` if the element is currently snapping to the center of the screen horizontally.

---

### isCurrentlySnappingVertically

**Added**: `1.0.0`

```java
boolean isCurrentlySnappingVertically()
```

Returns `true` if the element is currently snapping to the center of the screen vertically.

---

### isEdited

**Added**: `1.0.0`

```java
boolean isEdited()
```

Returns `true` if the element's current value is different from the value it had when the screen was initialized.

---

### isNotDefault

**Added**: `1.0.0`

```java
boolean isNotDefault()
```

Returns `true` if the element's current value is different from its assigned default value.  
This requires the element to have a valid default value set.

---

### save

**Added**: `1.0.0`

```java
void save()
```

Saves the current value of this element. This is called automatically when saving the parent screen.

---

### resetValue

**Added**: `1.0.0`

```java
void resetValue()
```

Resets the element's current value back to its default value.  
This requires the element to have a valid default value set.
