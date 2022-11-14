package hexlet.code.formatters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.differ.DiffClass;
import hexlet.code.Operation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


public final class JsonFormatter implements Formatter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(Map<String, DiffClass> resultMap) throws IOException {
        String delimiter = "\n";

        var sortedResult = resultMap.keySet().stream().sorted().filter((key) -> resultMap.get(key).getOperation()
                != Operation.UNCHAHGED).map((key) -> {
                    DiffClass diffClass = resultMap.get(key);
                    var newValue = diffClass.getValue2();
                    Operation operation = diffClass.getOperation();

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

        return Arrays.toString(sortedResult);

    }

    private class PatchObject {
        private String op;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String value;
        private String path;

        PatchObject(String op1, String path1, Object value1) {
            op = op1;
            value = value1.toString();
            path = path1;
        }

        PatchObject(String op1, String path1) {
            op = op1;
            path = path1;
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

