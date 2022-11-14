package hexlet.code.formatters;

import hexlet.code.differ.DiffEntity;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter<String> {


    @Override
    public String getName() {
        return "plain";
    }

    @Override
    public String add(DiffEntity diffEntity) {
        return "Property "
               + "'"
               + diffEntity.getKey()
               + "'"
               + " was added with value: "
               + valueToString(diffEntity.getValue2());
    }

    @Override
    public String remove(DiffEntity diffEntity) {
        return "Property " + "'" + diffEntity.getKey() + "'" + " was removed";
    }

    @Override
    public String replace(DiffEntity diffEntity) {
        return "Property "
               + "'"
               + diffEntity.getKey()
               + "'"
               + " was updated. From "
               + valueToString(diffEntity.getValue1())
               + " to "
               + valueToString(diffEntity.getValue2());
    }

    @Override
    public String wrap(List<String> differences) {
        String delimiter = "\n";
        return String.join(delimiter, differences);
    }

    private String valueToString(Object value) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
