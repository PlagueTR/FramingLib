package space.plague.framinglib.gui.elements.layoutelement;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@ApiStatus.Internal
public class LayoutResetButtonElement extends AbstractLayoutTextureButtonElement {

    public LayoutResetButtonElement(FramingLayoutElement layoutElement, int offsetX, int offsetY, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(layoutElement, offsetX, offsetY, name, buttonTextureHolder);
        setTooltipSupplier(
            () -> Optional.of(TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET)
        );
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.HINT, Component.translatable(TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET_BUTTON_STRING, this.layoutElement.getMessage()));
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
