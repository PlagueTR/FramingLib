package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import space.plague.framinglib.api.LayoutConfigScreenBuilder;
import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.references.TextureReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class LayoutConfigScreenBuilderImpl implements LayoutConfigScreenBuilder {

    private Component title = TranslationReferences.CONFIG_TITLE;

    private Screen parent = null;

    private Runnable savingRunnable = null;

    private boolean doesConfirmSave = true;
    private boolean doesShowButtons = true;

    private Alignments buttonsAlignment = Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.TOP);

    private boolean doesShowResetButton = true;

    private Consumer<Screen> afterInitConsumer = screen -> {};

    private ResourceLocation backgroundTexture = TextureReferences.DEFAULT_BACKGROUND;
    private boolean transparentBackground = false;

    private final List<LayoutElement> layoutElementList = new ArrayList<>();

    public LayoutConfigScreenBuilderImpl() {

    }

    @Override
    public Component getTitle() {
        return title;
    }
    @Override
    public LayoutConfigScreenBuilder setTitle(Component title) {
        this.title = title;
        return this;
    }

    @Override
    public Screen getParentScreen() {
        return parent;
    }
    @Override
    public LayoutConfigScreenBuilder setParentScreen(Screen parentScreen) {
        this.parent = parentScreen;
        return this;
    }

    @Override
    public Runnable getSavingRunnable() {
        return savingRunnable;
    }
    @Override
    public LayoutConfigScreenBuilder setSavingRunnable(Runnable savingRunnable) {
        this.savingRunnable = savingRunnable;
        return this;
    }

    @Override
    public boolean doesConfirmSave() {
        return doesConfirmSave;
    }

    @Override
    public LayoutConfigScreenBuilder setDoesConfirmSave(boolean doesConfirmSave) {
        this.doesConfirmSave = doesConfirmSave;
        return this;
    }

    @Override
    public boolean doesShowButtons() {
        return doesShowButtons;
    }
    @Override
    public LayoutConfigScreenBuilder setDoesShowButtons(boolean doesShowButtons) {
        this.doesShowButtons = doesShowButtons;
        return this;
    }

    @Override
    public Alignments getButtonsAlignment() {
        return this.buttonsAlignment;
    }

    @Override
    public LayoutConfigScreenBuilder setButtonsAlignment(Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment) {
        this.buttonsAlignment = Alignments.create(hAlignment, vAlignment);
        return this;
    }

    @Override
    public LayoutConfigScreenBuilder setButtonsAlignment(Alignments buttonsAlignment) {
        this.buttonsAlignment = buttonsAlignment;
        return this;
    }

    @Override
    public boolean doesShowResetButton() {
        return this.doesShowResetButton;
    }

    @Override
    public LayoutConfigScreenBuilder setShowResetButton(boolean showResetButton) {
        this.doesShowResetButton = showResetButton;
        return this;
    }

    @Override
    public Consumer<Screen> getAfterInitConsumer() {
        return afterInitConsumer;
    }
    @Override
    public LayoutConfigScreenBuilder setAfterInitConsumer(Consumer<Screen> afterInitConsumer) {
        this.afterInitConsumer = afterInitConsumer;
        return this;
    }

    @Override
    public ResourceLocation getBackgroundTexture() {
        return this.backgroundTexture;
    }
    @Override
    public LayoutConfigScreenBuilder setBackgroundTexture(ResourceLocation backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        return this;
    }

    @Override
    public boolean hasTransparentBackground() {
        return  transparentBackground;
    }
    @Override
    public LayoutConfigScreenBuilder setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
        return this;
    }

    @Override
    public LayoutConfigScreenBuilder addLayoutElementEntry(LayoutElement elementEntry) {
        this.layoutElementList.add(elementEntry);
        return this;
    }

    @Override
    public Screen build() {
        FramingLayoutConfigScreen screen = new FramingLayoutConfigScreen(parent, title, layoutElementList, backgroundTexture);
        screen.setSavingRunnable(savingRunnable);
        screen.setTransparentBackground(transparentBackground);
        screen.setShowButtons(doesShowButtons);
        screen.setButtonsAlignments(buttonsAlignment);
        screen.setShowResetButton(doesShowResetButton);
        screen.setConfirmSave(doesConfirmSave);
        screen.setAfterInitConsumer(afterInitConsumer);
        return screen;
    }
}
