package space.plague.framinglib.mixin;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import space.plague.framinglib.impl.WindowResizeNotifier;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "resizeDisplay()V", at = @At("HEAD"))
    private void onResize(CallbackInfo ci) {
        WindowResizeNotifier.onResize();
    }

}
