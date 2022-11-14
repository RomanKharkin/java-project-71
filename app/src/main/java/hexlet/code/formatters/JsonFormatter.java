package hexlet.code.formatters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.differ.DiffEntity;
import hexlet.code.exceptions.FormatFailedException;

import java.util.List;


public final class JsonFormatter implements Formatter<Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getName() {
        return "json";
    }

    @Override
    public Object add(DiffEntity diffEntity) {

        return new PatchObject("add", diffEntity.getKey(), diffEntity.getValue2());

    }

    @Override
    public Object remove(DiffEntity diffEntity) {

        return new PatchObject("remove", diffEntity.getKey());

    }

    @Override
    public Object replace(DiffEntity diffEntity) {

        return new PatchObject("replace", diffEntity.getKey(), diffEntity.getValue2());
    }

    @Override
    public String wrap(List<Object> differences) throws FormatFailedException {
        try {

            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differences);
        } catch (JsonProcessingException e) {
            throw new FormatFailedException("Failed to format", e);
        }
    }


    private class PatchObject {
        private final String op;
        private final String path;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String value;

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
    }
}

