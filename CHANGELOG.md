**1.1.0**:

- Support for Minecraft 1.20
- Rendering functions rewritten for 1.20

**API Changes**:

- Created `LayoutElementButton` and `LayoutElementButtonBuilder` interface to allow making custom buttons for layout elements
- Added `addCustomButton()` to `LayoutElementBuilder` to allow attaching said custom buttons to layout elements
- API now uses `GuiGraphics` instead of `PoseStack` as Minecraft's own GUI rendering now uses `GuiGraphics` class to render its GUI
- Added `getRegion()` to `TextureInfo`
