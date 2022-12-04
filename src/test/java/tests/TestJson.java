package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.InputStream;

public class TestJson {
    ClassLoader cl = ZipFileTest.class.getClassLoader();

    @Test
    void jsonParseTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream resources = cl.getResourceAsStream("person.json");
        ) {
            PersonJsonParse personJsonParse = objectMapper.readValue(resources, PersonJsonParse.class);
            assertThat(personJsonParse.firstName).isEqualTo("Joe");
            assertThat(personJsonParse.lastName).isEqualTo("Jackson");
            assertThat(personJsonParse.gender).isEqualTo("male");
            assertThat(personJsonParse.age).isEqualTo(28);

            assertThat(personJsonParse.phoneNumbers.get(0).type).isEqualTo("home");
            assertThat(personJsonParse.phoneNumbers.get(0).number).isEqualTo("7349282382");

        }
    }
}

