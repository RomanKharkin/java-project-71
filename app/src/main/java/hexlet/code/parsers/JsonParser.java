package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.exceptions.ParsingFailedException;

import java.util.Map;

public final class JsonParser implements Parser {

    public Map<String, Object> parse(String content) throws ParsingFailedException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new ParsingFailedException("Failed to parse JsonParser", e);
        }
    }
}
