package space.plague.framinglib.gui.elements;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolderImpl;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
public class FramingLayoutConfigBackButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigBackButton(FramingLayoutConfigScreen parent, int x, int y, Component name, ButtonTextureHolderImpl buttonTextureHolder) {
        super(parent, x, y, name, () -> buttonTextureHolder);
        setTooltipSupplier(
            () -> {
                if (screen.isEdited()) {
                    return Optional.of(TranslationReferences.CONFIG_CANCEL_DISCARD);
                }
                else {
                    return Optional.of(TranslationReferences.CONFIG_CANCEL);
                }
            }
        );
    }

    @Override
    public void onPress() {
        screen.quit();
    }
}
