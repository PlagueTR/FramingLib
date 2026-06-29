package space.plague.framinglib.api;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.gui.elements.layoutelement.FramingLayoutElement;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutElementBuilder {

    LayoutElementBuilder setSaveConsumer(Consumer<AlignmentSizeOffset> saveConsumer);

    LayoutElementBuilder setDefaultAlignmentSizeOffset(Supplier<AlignmentSizeOffset> defaultValue);
    LayoutElementBuilder setDefaultAlignmentSizeOffset(AlignmentSizeOffset defaultValue);

    LayoutElementBuilder setShowName(boolean showName);
    LayoutElementBuilder setNameAlignment(@NotNull Alignments nameAlignment);
    LayoutElementBuilder setNameAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setShowIcon(boolean showIcon);
    LayoutElementBuilder setIcon(TextureInfo textureInfo);
    LayoutElementBuilder setIconAlignment(@NotNull Alignments iconAlignment);
    LayoutElementBuilder setIconAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setColor(Color color);

    LayoutElementBuilder setDoesDrawBackground(boolean doesDrawBackground);

    LayoutElementBuilder setCustomRenderingFunction(BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction);

    LayoutElementBuilder setSnapping(boolean snapping);

    LayoutElementBuilder setButtonsAlignment(@NotNull Alignments buttonsAlignment);
    LayoutElementBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);

    LayoutElementBuilder setEnableResetButton(boolean enableResetButton);

    LayoutElement build();

}
