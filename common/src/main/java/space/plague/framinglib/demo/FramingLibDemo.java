package space.plague.framinglib.demo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import space.plague.framinglib.api.LayoutElementButtonBuilder;
import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.LayoutConfigScreenBuilder;
import space.plague.framinglib.api.LayoutElementBuilder;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.util.references.GraphicsReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class FramingLibDemo {

    public static final AlignmentSizeOffset element1_default = AlignmentSizeOffset.create(20, 20, 40, 40, Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP), Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));
    public static final AlignmentSizeOffset element1 = AlignmentSizeOffset.create(20, 20, 40, 40, Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP), Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));

    public static final AlignmentSizeOffset element2_default = AlignmentSizeOffset.create(20, 20, 100, 40, Alignments.create(Alignments.HAlignment.RIGHT, Alignments.VAlignment.TOP), Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));
    public static final AlignmentSizeOffset element2 = AlignmentSizeOffset.create(20, 20, 100, 40, Alignments.create(Alignments.HAlignment.RIGHT, Alignments.VAlignment.TOP), Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));

    public static LayoutConfigScreenBuilder getDemoLayoutConfigScreenBuilder(){
        LayoutConfigScreenBuilder builder = LayoutConfigScreenBuilder.create()
            .setParentScreen(Minecraft.getInstance().screen)
            .setTitle(TranslationReferences.DEMO_CONFIG_TITLE)
            .setTransparentBackground(true)
            ;

        LayoutElementBuilder layoutElement1 = builder.startLayoutElement(
            element1,
            TranslationReferences.DEMO_ELEMENT_1)
            .setDefaultValue(element1_default)
            .setShowName(false)
            .setIcon(GraphicsReferences.DEMO_ELEMENT_ICON)
            .setIconAlignment(Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.CENTER))
            .setDoesDrawBackground(false)
            .setSnapping(false)
            .setEnableResetButton(false)
            ;

        builder.addLayoutElementEntry(layoutElement1.build());

        LayoutElementBuilder layoutElement2 = builder.startLayoutElement(
            element2,
            TranslationReferences.DEMO_ELEMENT_2)
            .setDefaultValue(element2_default)
            .setNameAlignment(Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.CENTER))
            .setColor(Color.create("#9e34eb"))
            ;

        LayoutElementButtonBuilder layoutElement2SaveButton = layoutElement2.startLayoutElementButton(
            layoutElementButton -> layoutElementButton.getLayoutElement().save(),
            layoutElementButton -> GraphicsReferences.LAYOUT_ELEMENT_RESET_BUTTON_HOLDER,
            Component.literal("Save")
        );

        layoutElement2SaveButton.setEnabledProvider(layoutElementButton -> layoutElementButton.getLayoutElement().isEdited())
            .setShouldUseLayoutElementTint(true)
            .setTooltipProvider(layoutElementButton -> Optional.of(Component.literal("Save")))
            ;

        layoutElement2.addLayoutElementButtonEntry(layoutElement2SaveButton.build());

        builder.addLayoutElementEntry(layoutElement2.build());

        return builder;
    }

}
