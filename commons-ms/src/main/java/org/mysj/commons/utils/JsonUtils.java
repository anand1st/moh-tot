package org.mysj.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.UncheckedIOException;

public enum JsonUtils {
    ;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer();

    public static String objToString(Object object) {
        try {
            return OBJECT_WRITER.writeValueAsString(object);
        } catch(JsonProcessingException ex) {
            throw new UncheckedIOException("Error converting object to string", ex);
        }
    }

    public static <E> E stringToObj(String content, Class<E> clazz) {
        try {
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch(JsonProcessingException ex) {
            throw new UncheckedIOException("Error converting string to object", ex);
        }
    }
}
