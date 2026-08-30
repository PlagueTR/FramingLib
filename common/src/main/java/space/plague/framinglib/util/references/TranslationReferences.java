package space.plague.framinglib.util.references;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class TranslationReferences {

    public static final Component CONFIG_TITLE = Component.translatable("text.framinglib.config_title");

    public static final Component CONFIG_CANCEL = Component.translatable("gui.cancel");
    public static final Component CONFIG_CANCEL_DISCARD = Component.translatable("text.framinglib.cancel_discard");

    public static final Component CONFIG_RESET_ALL = Component.translatable("text.framinglib.reset_all");
    public static final Component CONFIG_SAVE = Component.translatable("text.framinglib.save");

    public static final Component CONFIG_LAYOUT_ELEMENT_RESET = Component.translatable("text.framinglib.layout_element_reset");

    public static final String CONFIG_LAYOUT_ELEMENT_BUTTON_NARRATION_STRING = "text.framinglib.layout_element_button_narration";

    public static final Component DEMO_CONFIG_TITLE = Component.translatable("text.framinglib.demo_config_title");

    public static final Component DEMO_ELEMENT_1 = Component.translatable("text.framinglib.demo_element_1");
    public static final Component DEMO_ELEMENT_2 = Component.translatable("text.framinglib.demo_element_2");
}
