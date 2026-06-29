package space.plague.framinglib.api.util;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.impl.TextureInfoImpl;

import java.awt.Rectangle;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface TextureInfo {

    static TextureInfo create(@NotNull ResourceLocation texture, @Nullable Rectangle region) {
        return new TextureInfoImpl(texture, region);
    }

    ResourceLocation getTexture();

    int getAtlasWidth();
    int getAtlasHeight();

    int getWidth();
    int getHeight();

    TextureUV getUV();

    boolean isPixelSolid(int x, int y);

    void render(PoseStack poseStack, int x, int y, Color color);

}
