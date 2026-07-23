package space.plague.framinglib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.impl.WindowResizeNotifierImpl;

@Environment(EnvType.CLIENT)
public class PositioningHelper {

    public static int windowWidth = -1, windowHeight = -1;

    public static int getActualX(AlignmentSizeOffset alignmentSizeOffset) {
        if (windowWidth == -1 || windowHeight == -1) {
            WindowResizeNotifierImpl.onResize();
            if (windowWidth == -1 || windowHeight == -1) {
                return 0;
            }
        }

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

        if (result < 0) {
            result = 0;
        }
        else if (result > windowWidth - alignmentSizeOffset.getScaledWidth()) {
            result = windowWidth - alignmentSizeOffset.getScaledWidth();
        }

        return result;
    }

    public static int getActualY(AlignmentSizeOffset alignmentSizeOffset) {
        if (windowWidth == -1 || windowHeight == -1) {
            WindowResizeNotifierImpl.onResize();
            if (windowWidth == -1 || windowHeight == -1) {
                return 0;
            }
        }

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

        if (result < 0) {
            result = 0;
        }
        else if (result > windowHeight - alignmentSizeOffset.getScaledHeight()) {
            result = windowHeight - alignmentSizeOffset.getScaledHeight();
        }

        return result;
    }

}
