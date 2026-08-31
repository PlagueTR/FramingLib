package space.plague.framinglib.gui.elements.layoutelement;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.util.references.GraphicsReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class DefaultLayoutElementButtons {

    static GenericLayoutElementTextureButton createResetButton() {
        GenericLayoutElementTextureButton resetButton = new GenericLayoutElementTextureButton(
            TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET,
            layoutElementButton -> layoutElementButton.getLayoutElement().resetValue(),
            layoutElementButton -> GraphicsReferences.LAYOUT_ELEMENT_RESET_BUTTON_HOLDER,
            layoutElementButton -> Optional.of(TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET),
            layoutElementButton -> layoutElementButton.getLayoutElement().isNotDefault()
        );
        resetButton.setShouldUseLayoutElementColor(true);
        return resetButton;
    }

}
