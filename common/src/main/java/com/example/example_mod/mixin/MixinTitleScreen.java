package com.example.example_mod.mixin;

import net.minecraft.client.gui.screens.TitleScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.example_mod.Main;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {

    private static boolean said_hello = false;

    @Inject(method = "init()V", at = @At("TAIL"))
    protected void init(CallbackInfo ci) {
        if (!said_hello && Main.getConfig().isEnableMod()) {
            Main.LOGGER.info("Hello from MixinTitleScreen");
            said_hello = true;
        }
    }

}
