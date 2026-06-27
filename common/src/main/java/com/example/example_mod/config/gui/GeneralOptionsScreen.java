package com.example.example_mod.config.gui;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import com.example.example_mod.Main;
import com.example.example_mod.config.ModConfig;

public class GeneralOptionsScreen {

    public static ConfigBuilder getConfigBuilder() {

        ModConfig defaults = new ModConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(Minecraft.getInstance().gui.screen())
                .setTitle(Component.literal(Main.MOD_NAME + " - General"));

        builder.setSavingRunnable(Main::saveConfig);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

        general.addEntry(entryBuilder.startBooleanToggle(Component.literal("Enable Mod"), Main.getConfig().isEnableMod())
                .setDefaultValue(defaults.isEnableMod())
                .setTooltip(Component.literal("Master switch to turn all mod features on or off."))
                .setSaveConsumer(newValue -> { Main.getConfig().setEnableMod(newValue); })
                .build());

        builder.transparentBackground();

        return builder;
    }

}