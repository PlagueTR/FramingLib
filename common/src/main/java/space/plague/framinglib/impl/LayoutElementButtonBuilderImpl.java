package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutElementButton;
import space.plague.framinglib.api.LayoutElementButtonBuilder;
import space.plague.framinglib.api.util.ButtonTextureHolder;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.gui.elements.layoutelement.GenericLayoutElementTextureButton;
import space.plague.framinglib.util.references.GraphicsReferences;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class LayoutElementButtonBuilderImpl implements LayoutElementButtonBuilder {
    @NotNull
    private final Component name;
    @NotNull
    private final Consumer<LayoutElementButton> onPressConsumer;
    @NotNull
    private final Function<LayoutElementButton, ButtonTextureHolder> buttonTextureHolderProvider;

    private boolean shouldUseLayoutElementTint = false;
    @NotNull
    private Color color = GraphicsReferences.WHITE;

    @Nullable
    private Function<LayoutElementButton, Boolean> enabledProvider = null;

    @Nullable
    private Function<LayoutElementButton, Optional<Component>> tooltipProvider = null;

    public LayoutElementButtonBuilderImpl(@NotNull Consumer<LayoutElementButton> onPressConsumer, @NotNull Function<LayoutElementButton, ButtonTextureHolder> buttonTextureHolderProvider, @NotNull Component name) {
        this.name = name;
        this.onPressConsumer = onPressConsumer;
        this.buttonTextureHolderProvider = buttonTextureHolderProvider;
    }

    @Override
    public LayoutElementButtonBuilder setShouldUseLayoutElementTint(boolean shouldUseLayoutElementTint) {
        this.shouldUseLayoutElementTint = shouldUseLayoutElementTint;
        return this;
    }

    @Override
    public LayoutElementButtonBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public LayoutElementButtonBuilder setEnabledProvider(Function<LayoutElementButton, Boolean> enabledProvider) {
        this.enabledProvider = enabledProvider;
        return this;
    }

    @Override
    public LayoutElementButtonBuilder setTooltipProvider(Function<LayoutElementButton, Optional<Component>> tooltipProvider) {
        this.tooltipProvider = tooltipProvider;
        return this;
    }

    @Override
    public LayoutElementButton build() {
        GenericLayoutElementTextureButton element = new GenericLayoutElementTextureButton(name, onPressConsumer, buttonTextureHolderProvider, tooltipProvider, enabledProvider);

        element.setShouldUseLayoutElementColor(shouldUseLayoutElementTint);
        element.setOnInitColor(color);

        return element;
    }

}
