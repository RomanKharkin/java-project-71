package hexlet.code.formatters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Operation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


public class JsonFormatter implements Formatter {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(Map<String, Differ.PairOfValues> resultMap) throws IOException {
        String delimiter = "\n";

        var sortedResult = resultMap.keySet().stream().sorted().filter((key) -> resultMap.get(key).getOperation()
                != null).map((key) -> {
                    Differ.PairOfValues pairOfValues = resultMap.get(key);
                    var newValue = pairOfValues.getValue2();
                    Operation operation = pairOfValues.getOperation();

                    try {
                        switch (operation) {
                            case ADD:
                                return new PatchObject("add", key, newValue).toJson();
                            case REMOVE:
                                return new PatchObject("remove", key).toJson();
                            case REPLACE:
                                return new PatchObject("replace", key, newValue).toJson();
                            default:
                                throw new RuntimeException("Неизвестная операция");
                        }
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).toArray();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/java/hexlet/code/jsonpatch.json"), Arrays.toString(sortedResult));

        return Arrays.toString(sortedResult);
    }

    private class PatchObject {
        private String op;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String value;
        private String path;

        PatchObject(String op, String path, Object value) {
            this.op = op;
            this.value = value.toString();
            this.path = path;
        }

        PatchObject(String op, String path) {
            this.op = op;
            this.path = path;
        }

        public String getOp() {
            return op;
        }

        public String getValue() {
            return value;
        }

        public String getPath() {
            return path;
        }

        public String toJson() throws JsonProcessingException {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(PatchObject.this);
        }
    }
}

