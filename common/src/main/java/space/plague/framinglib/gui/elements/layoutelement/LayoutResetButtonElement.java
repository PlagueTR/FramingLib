package space.plague.framinglib.gui.elements.layoutelement;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TranslationReferences;

public class LayoutResetButtonElement extends AbstractLayoutTextureButtonElement {

    public LayoutResetButtonElement(FramingLayoutElement layoutElement, int offsetX, int offsetY, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(layoutElement, offsetX, offsetY, name, buttonTextureHolder);
    }

    @Override
    protected MutableComponent createNarrationMessage() {
        return new TranslatableComponent(TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET_BUTTON_STRING, this.layoutElement.getMessage());
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        active = layoutElement.isNotDefault();
        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onPress() {
        layoutElement.resetValue();
    }

}
