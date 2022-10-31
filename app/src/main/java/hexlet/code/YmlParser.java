package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class YmlParser implements Parser {

    public Map<String, Object> parse(String content) throws IOException {

        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, Map.class);

    }

}
