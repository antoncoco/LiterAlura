package com.antoncoco.literalura.enums;

import com.antoncoco.literalura.utils.IEnumMenuOptionsMarker;
import lombok.Getter;

@Getter
public enum BookLanguageOptions implements IEnumMenuOptionsMarker {
    ENGLISH("Inglés", "en"),
    FRENCH("Francés", "fr");

    private final String label;
    private final String abbreviation;

    BookLanguageOptions(String label, String abbreviation) {
        this.label = label;
        this.abbreviation = abbreviation;
    }
}
