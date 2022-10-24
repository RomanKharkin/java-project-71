package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonToMapMapper {
    public static Map<String, Object> convert(String content) throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(content, Map.class);
    }
}
