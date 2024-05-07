package com.antoncoco.literalura.utils;

public interface IDataMapping {
    <T> T convertDataFromString(String data, Class<T> source);
}
