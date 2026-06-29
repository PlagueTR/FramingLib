package space.plague.framinglib.util.references;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import space.plague.framinglib.Main;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.NineSliceButtonTextureHolder;

import java.awt.Rectangle;

@Environment(EnvType.CLIENT)
public class TextureReferences {

    public static final ResourceLocation DEFAULT_BACKGROUND = Screen.BACKGROUND_LOCATION;

    public static final ResourceLocation FRAMING_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/texture.png");

    public static final ButtonTextureHolder ACCEPT_BUTTON_HOLDER = new ButtonTextureHolder(
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 128, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(144, 128, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(160, 128, 16, 16))
    );

    public static final ButtonTextureHolder DENY_BUTTON_HOLDER = new ButtonTextureHolder(
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 144, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(144, 144, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(160, 144, 16, 16))
    );

    public static final ButtonTextureHolder RESET_BUTTON_HOLDER = new ButtonTextureHolder(
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 160, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(144, 160, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(160, 160, 16, 16))
    );

    public static final ButtonTextureHolder BACK_BUTTON_HOLDER = new ButtonTextureHolder(
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 176, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(144, 176, 16, 16)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(160, 176, 16, 16))
    );

    public static final NineSliceButtonTextureHolder LAYOUT_ELEMENT_BACKGROUND_HOLDER = new NineSliceButtonTextureHolder(
        new NineSliceButtonTextureHolder.NineSliceTexture(
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(0, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(3, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(6, 0, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(0, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(3, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(6, 3, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(0, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(3, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(6, 6, 3, 3))
        ),
        new NineSliceButtonTextureHolder.NineSliceTexture(
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(9, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(12, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(15, 0, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(9, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(12, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(15, 3, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(9, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(12, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(15, 6, 3, 3))
        ),
        new NineSliceButtonTextureHolder.NineSliceTexture(
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(18, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(21, 0, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(24, 0, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(18, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(21, 3, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(24, 3, 3, 3)),
            TextureInfo.create(FRAMING_TEXTURE, new Rectangle(18, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(21, 6, 3, 3)), TextureInfo.create(FRAMING_TEXTURE, new Rectangle(24, 6, 3, 3))
        )
    );

    public static final ButtonTextureHolder LAYOUT_ELEMENT_RESET_BUTTON_HOLDER = new ButtonTextureHolder(
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 0, 8, 8)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(136, 0, 8, 8)),
        TextureInfo.create(FRAMING_TEXTURE, new Rectangle(144, 0, 8, 8))
    );

    public static final TextureInfo DEMO_ELEMENT_ICON = TextureInfo.create(FRAMING_TEXTURE, new Rectangle(128, 88, 40, 40));

}
