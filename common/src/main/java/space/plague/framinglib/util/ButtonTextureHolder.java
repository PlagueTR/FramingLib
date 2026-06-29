package space.plague.framinglib.util;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;

@Environment(EnvType.CLIENT)
public class ButtonTextureHolder {

    public enum ButtonState {
        DISABLED,
        ACTIVE,
        HOVERED
    }

    @NotNull
    private final TextureInfo disabled;
    @Nullable
    private final TextureInfo active;
    @Nullable
    private final TextureInfo hovered;

    public ButtonTextureHolder(@NotNull TextureInfo disabled, @Nullable TextureInfo active, @Nullable TextureInfo hovered) {
        this.disabled = disabled;
        this.active = active;
        this.hovered = hovered;
    }

    public @NotNull TextureInfo getDisabled() {
        return disabled;
    }
    public @Nullable TextureInfo getActive() {
        return active;
    }
    public @Nullable TextureInfo getHovered() {
        return hovered;
    }

    public void render(PoseStack poseStack, int x, int y, ButtonState state, Color color) {
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

        toDraw.render(poseStack, x, y, color);

    }

}
