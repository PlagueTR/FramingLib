package space.plague.framinglib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.ButtonState;
import space.plague.framinglib.api.util.ButtonTextureHolder;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public class ButtonTextureHolderImpl implements ButtonTextureHolder {

    private final @NotNull TextureInfo disabled;
    private final @Nullable TextureInfo active;
    private final @Nullable TextureInfo hovered;

    public ButtonTextureHolderImpl(@NotNull TextureInfo disabled, @Nullable TextureInfo active, @Nullable TextureInfo hovered) {
        this.disabled = disabled;
        this.active = active;
        this.hovered = hovered;
    }

    public void render(GuiGraphics guiGraphics, int x, int y, ButtonState state, Color color) {
        TextureInfo toDraw;
        switch (state) {
            case ACTIVE:
                toDraw = active == null ? disabled : active;
                break;
            case HOVERED:
                toDraw = hovered == null ? disabled : hovered;
                break;
            default:
                toDraw = disabled;
        }

        toDraw.render(guiGraphics, x, y, color);
    }

    @Override
    public @NotNull TextureInfo getDisabledTextureInfo() {
        return disabled;
    }

    @Override
    public @Nullable TextureInfo getActiveTextureInfo() {
        return active;
    }

    @Override
    public @Nullable TextureInfo getHoveredTextureInfo() {
        return hovered;
    }

}
