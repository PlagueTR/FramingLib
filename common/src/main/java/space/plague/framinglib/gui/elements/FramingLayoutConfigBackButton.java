package space.plague.framinglib.gui.elements;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

public class FramingLayoutConfigBackButton extends AbstractTextureButtonElement {

    public FramingLayoutConfigBackButton(Screen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(parent, x, y, name, buttonTextureHolder);
        setTooltipSupplier(
            () -> {
                if (screen instanceof FramingLayoutConfigScreen) {
                    FramingLayoutConfigScreen framingScreen = (FramingLayoutConfigScreen) screen;
                    if (framingScreen.isEdited()) {
                        return Optional.of(TranslationReferences.CONFIG_CANCEL_DISCARD);
                    }
                    else {
                        return Optional.of(TranslationReferences.CONFIG_CANCEL);
                    }
                }
                return Optional.empty();
            }
        );
    }

    @Override
    public void onPress() {
        if (screen instanceof FramingLayoutConfigScreen) {
            FramingLayoutConfigScreen framingScreen = (FramingLayoutConfigScreen) screen;
            framingScreen.quit();
        }
    }
}
