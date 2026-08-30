package space.plague.framinglib.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.ButtonState;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class NineSliceButtonTextureHolder {

    @NotNull
    private final NineSliceTexture disabled;
    @Nullable
    private final NineSliceTexture active;
    @Nullable
    private final NineSliceTexture hovered;

    public NineSliceButtonTextureHolder(@NotNull NineSliceTexture disabled, @Nullable NineSliceButtonTextureHolder.NineSliceTexture active, @Nullable NineSliceButtonTextureHolder.NineSliceTexture hovered) {
        this.disabled = disabled;
        this.active = active;
        this.hovered = hovered;
    }

    public void render(GuiGraphics guiGraphics, int x, int y, int width, int height, ButtonState state, Color color) {
        NineSliceTexture toDraw;
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

        RenderSystem.enableBlend();

        guiGraphics.setColor(
            color.getRedFloat(),
            color.getGreenFloat(),
            color.getBlueFloat(),
            color.getAlphaFloat()
        );

        TextureInfo textureInfo = toDraw.getTextureInfos()[0][0];
        guiGraphics.blit(textureInfo.getTexture(), x, y, textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[0][2];
        guiGraphics.blit(textureInfo.getTexture(), x + width - textureInfo.getWidth(), y, textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[2][0];
        guiGraphics.blit(textureInfo.getTexture(), x, y + height - textureInfo.getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[2][2];
        guiGraphics.blit(textureInfo.getTexture(), x + width - textureInfo.getWidth(), y + height - textureInfo.getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[0][1];
        guiGraphics.blit(textureInfo.getTexture(), x + toDraw.getTextureInfos()[0][0].getWidth(), y, width - toDraw.getTextureInfos()[0][0].getWidth() - toDraw.getTextureInfos()[0][2].getWidth(), textureInfo.getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[2][1];
        guiGraphics.blit(textureInfo.getTexture(), x + toDraw.getTextureInfos()[2][0].getWidth(), y + height - textureInfo.getHeight(), width - toDraw.getTextureInfos()[2][0].getWidth() - toDraw.getTextureInfos()[2][2].getWidth(), textureInfo.getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[1][0];
        guiGraphics.blit(textureInfo.getTexture(), x, y + toDraw.getTextureInfos()[0][0].getHeight(), textureInfo.getWidth(), height - toDraw.getTextureInfos()[0][0].getHeight() - toDraw.getTextureInfos()[2][0].getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[1][2];
        guiGraphics.blit(textureInfo.getTexture(), x + width - textureInfo.getWidth(), y + toDraw.getTextureInfos()[0][2].getHeight(), textureInfo.getWidth(), height - toDraw.getTextureInfos()[0][2].getHeight() - toDraw.getTextureInfos()[2][2].getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        textureInfo = toDraw.getTextureInfos()[1][1];
        guiGraphics.blit(textureInfo.getTexture(), x + toDraw.getTextureInfos()[1][0].getWidth(), y + toDraw.getTextureInfos()[0][1].getHeight(), width - toDraw.getTextureInfos()[1][0].getWidth() - toDraw.getTextureInfos()[1][2].getWidth(), height - toDraw.getTextureInfos()[0][1].getHeight() - toDraw.getTextureInfos()[2][1].getHeight(), textureInfo.getRegion().x, textureInfo.getRegion().y, textureInfo.getWidth(), textureInfo.getHeight(), textureInfo.getAtlasWidth(), textureInfo.getAtlasHeight());

        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        RenderSystem.disableBlend();
    }

    public static class NineSliceTexture {

        @NotNull
        private final TextureInfo[][] textureInfos;

        public NineSliceTexture(@NotNull TextureInfo topLeft, @NotNull TextureInfo top, @NotNull TextureInfo topRight,
                                @NotNull TextureInfo left, @NotNull TextureInfo center, @NotNull TextureInfo right,
                                @NotNull TextureInfo bottomLeft, @NotNull TextureInfo bottom, @NotNull TextureInfo bottomRight) {
            this.textureInfos = new TextureInfo[][] {
                { topLeft,    top,    topRight    },
                { left,       center, right       },
                { bottomLeft, bottom, bottomRight }
            };
        }

        public @NotNull TextureInfo[][] getTextureInfos() {
            return textureInfos;
        }

    }
}
