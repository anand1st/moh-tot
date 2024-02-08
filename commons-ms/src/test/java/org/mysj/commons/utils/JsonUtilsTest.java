package org.mysj.commons.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {

    @Test
    void testSerialization() {
        var dto = new MyDto("MyData", "YourData");
        var result = JsonUtils.objToString(dto);
        var expected = """
                {"myData":"MyData","yourData":"YourData"}""";
        assertEquals(expected, result);
    }

    @Test
    void testDeserialization() {
        var json = """
                {"myData":"MyData","yourData":"YourData"}""";
        var result = JsonUtils.stringToObj(json, MyDto.class);
        assertEquals(new MyDto("MyData", "YourData"), result);
    }

    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    @lombok.Data
    private static class MyDto {

        private String myData;
        private String yourData;
    }
}
