package space.plague.framinglib.util.references;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

@Environment(EnvType.CLIENT)
public class TranslationReferences {

    public static final Component CONFIG_TITLE = new TranslatableComponent("text.framinglib.config_title");

    public static final Component CONFIG_QUIT_TITLE = new TranslatableComponent("text.framinglib.quit_config");
    public static final Component CONFIG_QUIT_DESC = new TranslatableComponent("text.framinglib.quit_config_sure");

    public static final Component CONFIG_QUIT_DISCARD = new TranslatableComponent("text.framinglib.quit_discard");
    public static final Component CONFIG_CANCEL_DISCARD = new TranslatableComponent("text.framinglib.cancel_discard");
    public static final Component CONFIG_CANCEL = new TranslatableComponent("gui.cancel");

    public static final Component CONFIG_RESET_ALL = new TranslatableComponent("text.framinglib.reset_all");
    public static final Component CONFIG_SAVE = new TranslatableComponent("text.framinglib.save");

    public static final Component CONFIG_LAYOUT_ELEMENT_RESET = new TranslatableComponent("text.framinglib.layout_element_reset");

    public static final String CONFIG_LAYOUT_ELEMENT_STRING = "text.framinglib.layout_element";
    public static final String CONFIG_LAYOUT_ELEMENT_RESET_BUTTON_STRING = "text.framinglib.reset_button";

    public static final Component DEMO_CONFIG_TITLE = new TranslatableComponent("text.framinglib.demo_config_title");

    public static final Component DEMO_ELEMENT_1 = new TranslatableComponent("text.framinglib.demo_element_1");
    public static final Component DEMO_ELEMENT_2 = new TranslatableComponent("text.framinglib.demo_element_2");

}
