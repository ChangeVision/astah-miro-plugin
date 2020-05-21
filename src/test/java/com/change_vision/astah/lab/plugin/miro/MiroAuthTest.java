package com.change_vision.astah.lab.plugin.miro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MiroAuthTest {
    @Test
    public void deserializeMiroAuth() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = "{\n" +
                "  \"token\": \"b8962f86-0d2c-4bee-b473-81a1beb501d0\",\n" +
                "  \"boardId\": \"o9J_ktwvOAQ=\"\n" +
                "}\n";
        final MiroAuth auth = mapper.readValue(json, MiroAuth.class);
        assertThat(auth.getUnsafeSSL(), is(nullValue()));
    }

    @Test
    public void deserializeMiroAuthUnsafeSSL() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = "{\n" +
                "  \"token\": \"b8962f86-0d2c-4bee-b473-81a1beb501d0\",\n" +
                "  \"boardId\": \"o9J_ktwvOAQ=\",\n" +
                "  \"unsafeSSL\": true\n" +
                "}\n";
        final MiroAuth auth = mapper.readValue(json, MiroAuth.class);
        assertThat(auth.getUnsafeSSL(), is(true));
    }


}
