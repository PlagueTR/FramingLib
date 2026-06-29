package space.plague.framinglib.mixin;

import com.mojang.blaze3d.platform.Window;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import space.plague.framinglib.impl.WindowResizeNotifierImpl;

@Mixin(Window.class)
public class MixinWindow {

    @Inject(method = "onResize(JII)V", at = @At("TAIL"))
    private void onResize(long l, int i, int j, CallbackInfo ci) {
        WindowResizeNotifierImpl.onResize();
    }

}
