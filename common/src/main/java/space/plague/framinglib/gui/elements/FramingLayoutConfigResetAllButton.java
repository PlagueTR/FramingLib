package space.plague.framinglib.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import org.lwjgl.glfw.GLFW;

import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
public class FramingLayoutConfigResetAllButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigResetAllButton(FramingLayoutConfigScreen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(parent, x, y, name, buttonTextureHolder);
        setTooltipSupplier(() -> Optional.of(TranslationReferences.CONFIG_RESET_ALL));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks){
        active = screen.isNotDefault();
        super.render(poseStack, mouseX, mouseY, partialTicks);
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
