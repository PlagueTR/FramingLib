package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutConfigScreen {

    void setSavingRunnable(@Nullable Runnable savingRunnable);

    void setAfterInitConsumer(@Nullable Consumer<Screen> afterInitConsumer);

    ResourceLocation getBackgroundTexture();

    List<LayoutElement> getLayoutElementList();

    boolean isEdited();
    boolean isNotDefault();

    void saveAll(boolean openOtherScreens);
    void resetAll();

}
