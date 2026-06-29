package space.plague.framinglib.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Matrix4f;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.api.util.TextureUV;

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

    public void render(PoseStack poseStack, int x, int y, int width, int height, ButtonTextureHolder.ButtonState state, Color color) {
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
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        TextureManager textureManager = Minecraft.getInstance().getTextureManager();
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        buffer.begin(7, DefaultVertexFormat.POSITION_TEX_COLOR);
        Matrix4f matrix = poseStack.last().pose();

        float r = color.getRedFloat();
        float g = color.getGreenFloat();
        float b = color.getBlueFloat();
        float a = color.getAlphaFloat();

        TextureInfo textureInfo = toDraw.getTextureInfos()[0][0];
        TextureUV textureUV = textureInfo.getUV();

        textureManager.bind(textureInfo.getTexture());

        buffer.vertex(matrix, x, y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y, 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x, y, 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[0][2];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y, 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y, 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[2][0];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x, y + height, 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y + height, 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x, y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[2][2];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y + height, 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[0][1];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + toDraw.getTextureInfos()[0][0].getWidth(), y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[0][2].getWidth(), y + textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[0][2].getWidth(), y, 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + toDraw.getTextureInfos()[0][0].getWidth(), y, 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[2][1];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + toDraw.getTextureInfos()[2][0].getWidth(), y + height, 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[2][2].getWidth(), y + height, 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[2][2].getWidth(), y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + toDraw.getTextureInfos()[2][0].getWidth(), y + height - textureInfo.getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[1][0];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x, y + height - toDraw.getTextureInfos()[2][0].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y + height - toDraw.getTextureInfos()[2][0].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + textureInfo.getWidth(), y + toDraw.getTextureInfos()[0][0].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x, y + toDraw.getTextureInfos()[0][0].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[1][2];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y + height - toDraw.getTextureInfos()[2][2].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + height - toDraw.getTextureInfos()[2][2].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + toDraw.getTextureInfos()[0][2].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - textureInfo.getWidth(), y + toDraw.getTextureInfos()[0][2].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        textureInfo = toDraw.getTextureInfos()[1][1];
        textureUV = textureInfo.getUV();

        buffer.vertex(matrix, x + toDraw.getTextureInfos()[1][0].getWidth(), y + height - toDraw.getTextureInfos()[2][1].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[1][2].getWidth(), y + height - toDraw.getTextureInfos()[2][1].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width - toDraw.getTextureInfos()[1][2].getWidth(), y + toDraw.getTextureInfos()[0][1].getHeight(), 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + toDraw.getTextureInfos()[1][0].getWidth(), y + toDraw.getTextureInfos()[0][1].getHeight(), 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        tesselator.end();
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
