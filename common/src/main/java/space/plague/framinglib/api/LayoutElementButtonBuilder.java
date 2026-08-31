package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.network.chat.Component;

import space.plague.framinglib.api.util.Color;

import java.util.Optional;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutElementButtonBuilder {

    LayoutElementButtonBuilder setShouldUseLayoutElementTint(boolean shouldUseLayoutElementTint);
    LayoutElementButtonBuilder setColor(Color color);

    LayoutElementButtonBuilder setEnabledProvider(Function<LayoutElementButton, Boolean> enabledProvider);

    LayoutElementButtonBuilder setTooltipProvider(Function<LayoutElementButton, Optional<Component>> tooltipProvider);

    LayoutElementButton build();

}
