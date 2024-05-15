package com.antoncoco.literalura.utils;

public interface IReusableSubMenuOptionsExecution<E extends Enum<? extends IEnumMenuOptionsMarker>,
        T extends Enum<? extends IEnumMenuOptionsMarker>> {
    void executeMenuOption(E mainMenuOption, T currentSubMenuOption);
}
