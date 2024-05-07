package com.antoncoco.literalura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONToObjectMapper implements IDataMapping {
    private final ObjectMapper mapper;

    public JSONToObjectMapper() {
        mapper = new ObjectMapper();
    }

    @Override
    public <T> T convertDataFromString(String data, Class<T> source) {
        try {
            return mapper.readValue(data, source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
