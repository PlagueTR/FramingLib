package space.plague.framinglib.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

public class FramingLayoutConfigSaveButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigSaveButton(Screen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(parent, x, y, name, buttonTextureHolder);
        setTooltipSupplier(() -> Optional.of(TranslationReferences.CONFIG_SAVE));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        active = screen instanceof FramingLayoutConfigScreen && ((FramingLayoutConfigScreen) screen).isEdited();
        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onPress() {
        if (screen instanceof FramingLayoutConfigScreen) {
            FramingLayoutConfigScreen framingScreen = (FramingLayoutConfigScreen) screen;
            framingScreen.saveAll(true);
        }
    }

}
