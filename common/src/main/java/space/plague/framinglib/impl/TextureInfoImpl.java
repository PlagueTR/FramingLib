package space.plague.framinglib.impl;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Matrix4f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.api.util.TextureUV;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class TextureInfoImpl implements TextureInfo {

    private static final int ALPHA_THRESHOLD = 16;

    @NotNull
    private final ResourceLocation texture;
    @Nullable
    private final Rectangle region;

    private int atlasWidth;
    private int atlasHeight;

    private final int width;
    private final int height;

    private final TextureUV textureUV;

    private boolean maskFailed = false;
    private BitSet mask;

    public TextureInfoImpl(@NotNull ResourceLocation texture, @Nullable Rectangle region) {
        this.texture = texture;
        this.region = region;

        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        Resource resource;
        try {
            resource = resourceManager.getResource(texture);
            try (InputStream stream = resource.getInputStream()) {
                NativeImage nativeImage = NativeImage.read(stream);

                atlasWidth = nativeImage.getWidth();
                atlasHeight = nativeImage.getHeight();

                nativeImage.close();
            }
            catch (Exception e) {
                atlasWidth = -1;
                atlasHeight = -1;
                throw new RuntimeException("Couldn't load resource " + texture, e);
            }
        }
        catch (IOException e) {
            atlasWidth = -1;
            atlasHeight = -1;
            throw new RuntimeException("Couldn't load resource " + texture, e);
        }

        if (region == null) {
            width = atlasWidth;
            height = atlasHeight;
        }
        else {
            width = region.width;
            height = region.height;
        }

        textureUV = TextureUV.createFromRegion(atlasWidth, atlasHeight, region);
    }

    @Override
    public @NotNull ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public int getAtlasWidth() {
        return atlasWidth;
    }

    @Override
    public int getAtlasHeight() {
        return atlasHeight;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public TextureUV getUV() {
        return textureUV;
    }

    private void generateMask() {
        if (atlasWidth == -1 || atlasHeight == -1) {
            maskFailed = true;
            return;
        }
        mask = new BitSet(width * height);

        int offsetX = region == null ? 0 : region.x;
        int offsetY = region == null ? 0 : region.y;

        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        Resource resource;
        try {
            resource = resourceManager.getResource(texture);
            try (InputStream stream = resource.getInputStream()) {
                NativeImage nativeImage = NativeImage.read(stream);

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (offsetX + x < atlasWidth && offsetY + y < atlasHeight) {
                            int color = nativeImage.getPixelRGBA(offsetX + x, offsetY + y);
                            int alpha = (color >> 24) & 0xFF;

                            mask.set(y * width + x, alpha >= ALPHA_THRESHOLD);
                        }
                    }
                }

                nativeImage.close();
            }
            catch (Exception e) {
                maskFailed = true;

                throw new RuntimeException("Couldn't load resource " + texture, e);
            }
        }
        catch (IOException e) {
            maskFailed = true;

            throw new RuntimeException("Couldn't load resource " + texture, e);
        }

    }

    @Override
    public boolean isPixelSolid(int x, int y) {
        if (mask == null && !maskFailed) {
            generateMask();
        }
        else if (mask == null) {
            return true;
        }
        return mask.get(y * width + x);
    }

    @Override
    public void render(PoseStack poseStack, int x, int y, Color color) {
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        TextureManager textureManager = Minecraft.getInstance().getTextureManager();
        textureManager.bind(texture);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        buffer.begin(7, DefaultVertexFormat.POSITION_TEX_COLOR);
        Matrix4f matrix = poseStack.last().pose();

        float r = color.getRedFloat();
        float g = color.getGreenFloat();
        float b = color.getBlueFloat();
        float a = color.getAlphaFloat();

        buffer.vertex(matrix, x, y + height, 0.0f).uv(textureUV.getUMin(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0.0f).uv(textureUV.getUMax(), textureUV.getVMax()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y, 0.0f).uv(textureUV.getUMax(), textureUV.getVMin()).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x, y, 0.0f).uv(textureUV.getUMin(), textureUV.getVMin()).color(r, g, b, a).endVertex();

        tesselator.end();
        RenderSystem.disableBlend();
    }

}
