package space.plague.framinglib.gui.elements;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import org.lwjgl.glfw.GLFW;

import space.plague.framinglib.api.util.ButtonTextureHolder;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolderImpl;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class FramingLayoutConfigResetAllButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigResetAllButton(FramingLayoutConfigScreen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(parent, x, y, name, () -> buttonTextureHolder instanceof ButtonTextureHolderImpl ? (ButtonTextureHolderImpl) buttonTextureHolder : null);
        setTooltipSupplier(() -> Optional.of(TranslationReferences.CONFIG_RESET_ALL));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        active = screen.isNotDefault();
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onPress() {
        screen.resetAll();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers){
        if (keyCode == GLFW.GLFW_KEY_R && active) {
            this.playDownSound(Minecraft.getInstance().getSoundManager());
            onPress();
            return true;
        }
        return false;
    }
}
