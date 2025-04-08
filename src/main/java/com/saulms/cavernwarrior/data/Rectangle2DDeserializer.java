package com.saulms.cavernwarrior.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.geometry.Rectangle2D;

import java.io.IOException;

public class Rectangle2DDeserializer extends JsonDeserializer<Rectangle2D> {

    @Override
    public Rectangle2D deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        double x = node.get("x").asInt();
        double y = node.get("y").asInt();
        double w = node.get("w").asInt();
        double h = node.get("h").asInt();
        return new Rectangle2D(x, y, w, h);
    }

}
