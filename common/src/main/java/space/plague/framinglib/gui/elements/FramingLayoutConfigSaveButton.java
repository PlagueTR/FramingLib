package space.plague.framinglib.gui.elements;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolderImpl;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
public class FramingLayoutConfigSaveButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigSaveButton(FramingLayoutConfigScreen parent, int x, int y, Component name, ButtonTextureHolderImpl buttonTextureHolder) {
        super(parent, x, y, name, () -> buttonTextureHolder);
        setTooltipSupplier(() -> Optional.of(TranslationReferences.CONFIG_SAVE));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        active = screen.isEdited();
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onPress() {
        screen.saveAll(true);
    }

}
