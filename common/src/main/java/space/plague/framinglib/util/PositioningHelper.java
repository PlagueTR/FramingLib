package space.plague.framinglib.util;

import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;

@Environment(EnvType.CLIENT)
public class PositioningHelper {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Window window = minecraft.getWindow();

    public static Alignments.HAlignment getHAlignment(int actualX, int scaledWidth) {
        int windowWidth = window.getGuiScaledWidth();

        int centerX = actualX + scaledWidth / 2;

        if (centerX <= windowWidth / 3) {
            return Alignments.HAlignment.LEFT;
        }
        else if (centerX >= (windowWidth * 2) / 3) {
            return Alignments.HAlignment.RIGHT;
        }
        return Alignments.HAlignment.MIDDLE;
    }

    public static int getOffsetX(int actualX, int scaledWidth) {
        int windowWidth = window.getGuiScaledWidth();

        int centerX = actualX + scaledWidth / 2;

        if (centerX <= windowWidth / 3) {
            return actualX;
        }
        else if (centerX >= (windowWidth * 2) / 3) {
            return windowWidth - actualX - scaledWidth;
        }
        return -((windowWidth - scaledWidth) / 2 - actualX);
    }

    public static Alignments.VAlignment getVAlignment(int actualY, int scaledHeight) {
        int windowHeight = window.getGuiScaledHeight();

        int centerY = actualY + scaledHeight / 2;

        if (centerY <= windowHeight / 3) {
            return Alignments.VAlignment.TOP;
        }
        else if (centerY >= (windowHeight * 2) / 3) {
            return Alignments.VAlignment.BOTTOM;
        }
        return Alignments.VAlignment.CENTER;
    }

    public static int getOffsetY(int actualY, int scaledHeight) {
        int windowHeight = window.getGuiScaledHeight();

        int centerY = actualY + scaledHeight / 2;

        if (centerY <= windowHeight / 3) {
            return actualY;
        }
        else if (centerY >= (windowHeight * 2) / 3) {
            return windowHeight - actualY - scaledHeight;
        }
        return -((windowHeight - scaledHeight) / 2 - actualY);
    }

    public static int getActualX(AlignmentSizeOffset alignmentSizeOffset) {
        int windowWidth = window.getGuiScaledWidth();

        int result = 0;
        switch(alignmentSizeOffset.getHAlignment()) {
            case LEFT:
                result = alignmentSizeOffset.getOffsetX();
                break;
            case RIGHT:
                result = windowWidth - alignmentSizeOffset.getScaledWidth() - alignmentSizeOffset.getOffsetX();
                break;
            case MIDDLE:
                result = (windowWidth - alignmentSizeOffset.getScaledWidth()) / 2 + alignmentSizeOffset.getOffsetX();
        }

        result = MathUtils.clamp(result, 0, windowWidth - alignmentSizeOffset.getScaledWidth());

        return result;
    }

    public static int getActualY(AlignmentSizeOffset alignmentSizeOffset) {
        int windowHeight = window.getGuiScaledHeight();

        int result = 0;

        switch (alignmentSizeOffset.getVAlignment()) {
            case TOP:
                result = alignmentSizeOffset.getOffsetY();
                break;
            case BOTTOM:
                result = windowHeight - alignmentSizeOffset.getScaledHeight() - alignmentSizeOffset.getOffsetY();
                break;
            case CENTER:
                result = (windowHeight - alignmentSizeOffset.getScaledHeight()) / 2 + alignmentSizeOffset.getOffsetY();
        }

        result = MathUtils.clamp(result, 0, windowHeight - alignmentSizeOffset.getScaledHeight());

        return result;
    }

}
