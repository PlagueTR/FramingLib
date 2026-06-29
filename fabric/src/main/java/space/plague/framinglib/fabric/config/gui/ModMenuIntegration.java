package space.plague.framinglib.fabric.config.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import space.plague.framinglib.demo.FramingLibDemo;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> FramingLibDemo.getDemoLayoutConfigScreenBuilder().build();
    }

}