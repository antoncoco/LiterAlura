package com.antoncoco.literalura.utils;

public interface IMenuOptionsExecution<E extends Enum<? extends IEnumMenuOptionsMarker>> {
     void executeMenuOption(E menuOption);
}
