package space.plague.framinglib.gui.elements.layoutelement;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.LayoutElementButton;
import space.plague.framinglib.api.util.ButtonTextureHolder;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.gui.elements.AbstractTextureButtonElement;
import space.plague.framinglib.util.ButtonTextureHolderImpl;
import space.plague.framinglib.util.references.GraphicsReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class GenericLayoutElementTextureButton extends AbstractTextureButtonElement implements LayoutElementButton {

    private FramingLayoutElement layoutElement;
    private int offsetX;
    private int offsetY;

    private final Consumer<LayoutElementButton> onPressConsumer;

    @NotNull
    private final Function<LayoutElementButton, ButtonTextureHolder> buttonTextureHolderProvider;

    private boolean shouldUseLayoutElementColor = false;
    private Color onInitColor = GraphicsReferences.WHITE;

    @Nullable
    private final Function<LayoutElementButton, Boolean> enabledProvider;

    @Nullable
    private final Function<LayoutElementButton, Optional<Component>> tooltipProvider;

    public GenericLayoutElementTextureButton(Component name, Consumer<LayoutElementButton> onPressConsumer, @NotNull Function<LayoutElementButton, ButtonTextureHolder> buttonTextureHolderProvider, @Nullable Function<LayoutElementButton, Optional<Component>> tooltipProvider, @Nullable Function<LayoutElementButton, Boolean> enabledProvider) {
        super (null, 0,  0, name, () -> null);

        this.onPressConsumer = onPressConsumer;

        this.buttonTextureHolderProvider = buttonTextureHolderProvider;

        this.enabledProvider = enabledProvider;
        this.tooltipProvider = tooltipProvider;

        this.buttonTextureSupplier = () -> {
            ButtonTextureHolder bth = this.buttonTextureHolderProvider.apply(this);
            if (bth instanceof ButtonTextureHolderImpl) {
                return (ButtonTextureHolderImpl) bth;
            }
            else {
                return null;
            }
        };
        this.width = buttonTextureSupplier.get().getDisabledTextureInfo().getWidth();
        this.height = buttonTextureSupplier.get().getDisabledTextureInfo().getHeight();

        this.tooltipSupplier = () -> {
            if (this.tooltipProvider != null) {
                return this.tooltipProvider.apply(this);
            }
            else {
                return Optional.empty();
            }
        };
    }

    public void setShouldUseLayoutElementColor(boolean shouldUseLayoutElementColor) {
        this.shouldUseLayoutElementColor = shouldUseLayoutElementColor;
    }

    public void setOnInitColor(Color onInitColor) {
        this.onInitColor = onInitColor;
    }

    public void init(FramingLayoutElement layoutElement, int offsetX, int offsetY) {
        this.layoutElement = layoutElement;

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.screen = this.layoutElement.screen;

        this.setX(this.layoutElement.getX() + this.offsetX);
        this.setY(this.layoutElement.getY() + this.offsetY);

        if (this.shouldUseLayoutElementColor) {
            setColor(layoutElement.getColor());
        }
        else {
            setColor(onInitColor);
        }
    }

    public void updatePosition() {
        this.setX(layoutElement.getX() + offsetX);
        this.setY(layoutElement.getY() + offsetY);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (enabledProvider != null) {
            active = enabledProvider.apply(this);
        }
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.HINT,
            Component.translatable(TranslationReferences.CONFIG_LAYOUT_ELEMENT_BUTTON_NARRATION_STRING, this.layoutElement.getMessage(), this.getMessage())
            );
    }

    public int getTextureWidth() {
        return buttonTextureHolderProvider.apply(this).getDisabledTextureInfo().getWidth();
    }

    public int getTextureHeight() {
        return buttonTextureHolderProvider.apply(this).getDisabledTextureInfo().getHeight();
    }

    @Override
    public void onPress() {
        onPressConsumer.accept(this);
    }

    @Override
    public LayoutElement getLayoutElement() {
        return layoutElement;
    }

    @Override
    public boolean isDisabled() {
        return !active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public boolean isHovered() {
        return isHovered;
    }

}
