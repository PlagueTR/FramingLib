package space.plague.framinglib.fabric.config.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.demo.FramingLibDemo;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> FramingLibDemo.getDemoLayoutConfigScreenBuilder().build();
    }

}
