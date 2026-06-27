package com.example.example_mod.fabric;

import net.fabricmc.api.ModInitializer;

import com.example.example_mod.Main;

public final class MainFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        Main.init();

    }
}