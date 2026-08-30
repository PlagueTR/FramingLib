package space.plague.framinglib.gui.elements.layoutelement;

import space.plague.framinglib.util.references.GraphicsReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.HashMap;
import java.util.Optional;

public class DefaultLayoutElementButtons {

    static GenericLayoutElementTextureButton createResetButton() {
        GenericLayoutElementTextureButton resetButton = new GenericLayoutElementTextureButton(
            TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET,
            layoutElementButton -> layoutElementButton.getLayoutElement().resetValue(),
            layoutElementButton -> GraphicsReferences.LAYOUT_ELEMENT_RESET_BUTTON_HOLDER,
            new HashMap<>(),
            layoutElementButton -> Optional.of(TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET),
            layoutElementButton -> layoutElementButton.getLayoutElement().isNotDefault()
        );
        resetButton.setShouldUseLayoutElementColor(true);
        return resetButton;
    }

}
