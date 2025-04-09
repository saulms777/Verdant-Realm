package com.saulms.verdantrealm.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.geometry.Rectangle2D;

import java.io.IOException;

public class Rectangle2DSerializer extends JsonSerializer<Rectangle2D> {

    @Override
    public void serialize(Rectangle2D rect, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("x", rect.getMinX());
        generator.writeNumberField("y", rect.getMinY());
        generator.writeNumberField("w", rect.getWidth());
        generator.writeNumberField("h", rect.getHeight());
        generator.writeEndObject();
    }

}
