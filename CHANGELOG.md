**1.1.0**:

- Support for Minecraft 1.20
- Rendering functions rewritten for 1.20

**API Changes**:

- API now uses `GuiGraphics` instead of `PoseStack` as Minecraft's own GUI rendering now uses `GuiGraphics` class to render its GUI
- Created `LayoutElementButton` and `LayoutElementButtonBuilder` interface to allow making custom buttons for layout elements
- Added `startLayoutElementButton()` and `addLayoutElementButtonEntry()` to `LayoutElementBuilder` to allow creating and attaching custom buttons to layout elements
- Made `ButtonState` and `ButtonTextureHolder` part of the API (it was internal before)
- Added `getRegion()` to `TextureInfo`
