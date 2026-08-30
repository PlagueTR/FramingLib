package space.plague.framinglib.api;

import net.minecraft.network.chat.Component;

import space.plague.framinglib.api.util.Color;

import java.util.Optional;
import java.util.function.Function;

public interface LayoutElementButtonBuilder {

    LayoutElementButtonBuilder setShouldUseLayoutElementTint(boolean shouldUseLayoutElementTint);
    LayoutElementButtonBuilder setColor(Color color);

    LayoutElementButtonBuilder setEnabledProvider(Function<LayoutElementButton, Boolean> enabledProvider);

    LayoutElementButtonBuilder setTooltipProvider(Function<LayoutElementButton, Optional<Component>> tooltipProvider);

    LayoutElementButtonBuilder setCustomData(String key, Object data);

    LayoutElementButton build();

}
