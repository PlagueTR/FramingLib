package space.plague.framinglib.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;

import org.jetbrains.annotations.ApiStatus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import space.plague.framinglib.util.DisplayResizeNotifier;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "resizeDisplay()V", at = @At("HEAD"))
    private void onResize(CallbackInfo ci) {
        DisplayResizeNotifier.onResize();
    }

}
