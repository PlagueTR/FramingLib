package space.plague.framinglib.impl.builders;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.LayoutElementBuilder;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.gui.elements.layoutelement.FramingLayoutElement;
import space.plague.framinglib.util.references.GraphicsReferences;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class LayoutElementBuilderImpl implements LayoutElementBuilder {
    @NotNull
    private final Component name;
    @NotNull
    private final AlignmentSizeOffset original;
    @Nullable
    private Supplier<AlignmentSizeOffset> defaultValue = null;

    private boolean showName = true;
    private Alignments nameAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_NAME_ALIGNMENT;

    private boolean showIcon = true;
    @Nullable
    private TextureInfo iconInfo;
    private Alignments iconAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_ICON_ALIGNMENT;

    @NotNull
    private Color color = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_COLOR;

    private boolean doesDrawBackground = true;

    @Nullable
    private BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction = null;

    private boolean snapping = true;

    private boolean enableResetButton = true;

    private boolean showButtons = true;
    @NotNull
    private Alignments buttonsAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_BUTTONS_ALIGNMENT;

    public LayoutElementBuilderImpl(@NotNull AlignmentSizeOffset alignmentOffsetIn, @NotNull Component name) {
        this.original = alignmentOffsetIn;
        this.name = name;
    }

    @Override
    public LayoutElementBuilder setDefaultValue(Supplier<AlignmentSizeOffset> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public LayoutElementBuilder setDefaultValue(AlignmentSizeOffset defaultValue) {
        this.defaultValue = () -> defaultValue;
        return this;
    }

    @Override
    public LayoutElementBuilder setShowName(boolean showName) {
        this.showName = showName;
        return this;
    }

    @Override
    public LayoutElementBuilder setNameAlignment(@NotNull Alignments nameAlignment) {
        this.nameAlignment = nameAlignment;
        return this;
    }

    @Override
    public LayoutElementBuilder setNameAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment) {
        this.nameAlignment = Alignments.create(hAlignment, vAlignment);
        return this;
    }

    @Override
    public LayoutElementBuilder setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    @Override
    public LayoutElementBuilder setIcon(TextureInfo textureInfo) {
        this.iconInfo = textureInfo;
        return this;
    }

    @Override
    public LayoutElementBuilder setIconAlignment(@NotNull Alignments iconAlignment) {
        this.iconAlignment = iconAlignment;
        return this;
    }

    @Override
    public LayoutElementBuilder setIconAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment) {
        this.iconAlignment = Alignments.create(hAlignment, vAlignment);
        return this;
    }

    @Override
    public LayoutElementBuilder setColor(Color color) {
        if (color == null){
            return this;
        }
        this.color = color;
        return this;
    }

    @Override
    public LayoutElementBuilder setDoesDrawBackground(boolean doesDrawBackground) {
        this.doesDrawBackground = doesDrawBackground;
        return this;
    }

    @Override
    public LayoutElementBuilder setCustomRenderingFunction(BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction) {
        this.customRenderingFunction = customRenderingFunction;
        return this;
    }

    @Override
    public LayoutElementBuilder setSnapping(boolean snapping) {
        this.snapping = snapping;
        return this;
    }

    @Override
    public LayoutElementBuilder setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
        return this;
    }

    @Override
    public LayoutElementBuilder setButtonsAlignment(@NotNull Alignments buttonsAlignment) {
        this.buttonsAlignment = buttonsAlignment;
        return this;
    }

    @Override
    public LayoutElementBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment) {
        this.buttonsAlignment = Alignments.create(hAlignment, vAlignment);
        return this;
    }

    @Override
    public LayoutElementBuilder setEnableResetButton(boolean enableResetButton) {
        this.enableResetButton = enableResetButton;
        return this;
    }

    @Override
    public LayoutElement build() {
        FramingLayoutElement element = new FramingLayoutElement(original, name, defaultValue);

        element.setShowName(showName);
        element.setNameAlignment(nameAlignment);

        element.setShowIcon(showIcon);
        element.setIconInfo(iconInfo);
        element.setIconAlignment(iconAlignment);

        element.setColor(color);
        element.setDoesDrawBackground(doesDrawBackground);
        element.setCustomRenderingFunction(customRenderingFunction);

        element.setSnapping(snapping);

        element.setShowButtons(showButtons);

        element.setEnableResetButton(enableResetButton);

        element.setButtonsAlignment(buttonsAlignment);

        return element;
    }

}
