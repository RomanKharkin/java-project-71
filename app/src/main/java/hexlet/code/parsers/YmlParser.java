package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.exceptions.ParsingFailedException;

import java.util.Map;

public final class YmlParser implements Parser {

    public Map<String, Object> parse(String content) throws ParsingFailedException {
        try {
            ObjectMapper mapper = new YAMLMapper();
            return mapper.readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new ParsingFailedException("Failed to parse YmlParser", e);
        }

    }

}
