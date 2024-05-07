package com.antoncoco.literalura.utils;

public class URLNormalizer {
    public static String normalizeSearchParameterWithSpaces(String searchParameter) {
        return searchParameter.replace(" ", "%20");
    }
}
