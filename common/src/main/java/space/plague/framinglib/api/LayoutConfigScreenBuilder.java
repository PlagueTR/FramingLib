package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.impl.LayoutConfigScreenBuilderImpl;
import space.plague.framinglib.impl.builders.LayoutElementBuilderImpl;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface LayoutConfigScreenBuilder {

    static LayoutConfigScreenBuilder create(){
        return new LayoutConfigScreenBuilderImpl();
    }

    Component getTitle();
    LayoutConfigScreenBuilder setTitle(Component title);

    Screen getParentScreen();
    LayoutConfigScreenBuilder setParentScreen(Screen parentScreen);

    Runnable getSavingRunnable();
    LayoutConfigScreenBuilder setSavingRunnable(Runnable savingRunnable);

    boolean doesConfirmSave();
    LayoutConfigScreenBuilder setDoesConfirmSave(boolean doesConfirmSave);

    boolean doesShowButtons();
    LayoutConfigScreenBuilder setDoesShowButtons(boolean doesShowButtons);

    Alignments getButtonsAlignment();
    LayoutConfigScreenBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment);
    LayoutConfigScreenBuilder setButtonsAlignment(Alignments buttonsAlignment);

    boolean doesShowResetButton();
    LayoutConfigScreenBuilder setShowResetButton(boolean showResetButton);

    Consumer<Screen> getAfterInitConsumer();
    LayoutConfigScreenBuilder setAfterInitConsumer(Consumer<Screen> afterInitConsumer);

    ResourceLocation getBackgroundTexture();
    LayoutConfigScreenBuilder setBackgroundTexture(ResourceLocation backgroundTexture);

    boolean hasTransparentBackground();
    LayoutConfigScreenBuilder setTransparentBackground(boolean transparentBackground);

    LayoutConfigScreenBuilder addLayoutElementEntry(LayoutElement elementEntry);

    default LayoutElementBuilder startLayoutElement(@NotNull AlignmentSizeOffset alignmentSizeOffset, Component name) {
        return new LayoutElementBuilderImpl(alignmentSizeOffset, name);
    }

    Screen build();

}
