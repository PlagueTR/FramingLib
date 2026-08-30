package space.plague.framinglib.api;

public interface LayoutElementButton {

    LayoutElement getLayoutElement();

    boolean isDisabled();
    boolean isEnabled();

    boolean isHovered();

    void setCustomData(String key, Object data);
    Object getCustomData(String key);

}
