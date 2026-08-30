package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;

import space.plague.framinglib.api.util.*;
import space.plague.framinglib.impl.LayoutElementButtonBuilderImpl;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutElementBuilder {

    LayoutElementBuilder setDefaultValue(Supplier<AlignmentSizeOffset> defaultValue);
    LayoutElementBuilder setDefaultValue(AlignmentSizeOffset defaultValue);

    LayoutElementBuilder setShowName(boolean showName);
    LayoutElementBuilder setNameAlignment(@NotNull Alignments nameAlignment);
    LayoutElementBuilder setNameAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setShowIcon(boolean showIcon);
    LayoutElementBuilder setIcon(TextureInfo textureInfo);
    LayoutElementBuilder setIconAlignment(@NotNull Alignments iconAlignment);
    LayoutElementBuilder setIconAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setColor(Color color);

    LayoutElementBuilder setDoesDrawBackground(boolean doesDrawBackground);

    LayoutElementBuilder setCustomRenderingFunction(BiConsumer<GuiGraphics, AlignmentSizeOffset> customRenderingFunction);

    LayoutElementBuilder setSnapping(boolean snapping);

    LayoutElementBuilder setShowButtons(boolean showButtons);
    LayoutElementBuilder setButtonsAlignment(@NotNull Alignments buttonsAlignment);
    LayoutElementBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setEnableResetButton(boolean enableResetButton);

    LayoutElementBuilder addLayoutElementButtonEntry(LayoutElementButton buttonEntry);

    default LayoutElementButtonBuilder startLayoutElementButton(Consumer<LayoutElementButton> onPressConsumer, Function<LayoutElementButton, ButtonTextureHolder> buttonTextureProvider, Component name) {
        return new LayoutElementButtonBuilderImpl(onPressConsumer, buttonTextureProvider, name);
    }

    LayoutElement build();

}
