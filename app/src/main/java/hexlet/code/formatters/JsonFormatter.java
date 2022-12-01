package hexlet.code.formatters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Operation;
import hexlet.code.differ.DiffEntity;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


public final class JsonFormatter implements Formatter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(Map<String, DiffEntity> resultMap) throws IOException {
        var sortedResult = resultMap.keySet()
                                   .stream()
                                   .sorted()
                                   .filter((key) -> resultMap.get(key).getOperation() != Operation.UNCHAHGED)
                                   .map((key) -> {
                                       DiffEntity diffClass = resultMap.get(key);
                                       var newValue = diffClass.getValue2();
                                       Operation operation = diffClass.getOperation();

                                       switch (operation) {
                                           case ADD:
                                               return new PatchObject("add", key, newValue);
                                           case REMOVE:
                                               return new PatchObject("remove", key);
                                           case REPLACE:
                                               return new PatchObject("replace", key, newValue);
                                           default:
                                               throw new RuntimeException("Неизвестная операция");
                                       }
                                   })
                                   .collect(Collectors.toList());

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedResult);

    }

    private class PatchObject {
        private String op;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String value;
        private String path;

        PatchObject(String op1, String path1, Object value1) {
            op = op1;
            value = value1 == null ? "null" : value1.toString();
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
    }
}

