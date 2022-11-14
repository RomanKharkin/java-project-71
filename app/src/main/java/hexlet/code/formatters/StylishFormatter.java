package hexlet.code.formatters;

import hexlet.code.differ.DiffEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class StylishFormatter implements Formatter<String> {
    private final String delimiter = "\n";

    @Override
    public String getName() {
        return "stylish";
    }

    @Override
    public String add(DiffEntity diffEntity) {
        return "+ " + diffEntity.getKey() + ": " + diffEntity.getValue2();
    }

    @Override
    public String remove(DiffEntity diffEntity) {
        return "- " + diffEntity.getKey() + ": " + diffEntity.getValue1();
    }

    @Override
    public String replace(DiffEntity diffEntity) {

        var val1 = "- " + diffEntity.getKey() + ": " + diffEntity.getValue1();
        var val2 = "+ " + diffEntity.getKey() + ": " + diffEntity.getValue2();
        return val1 + delimiter + val2;
    }

    @Override
    public String nothing(DiffEntity diffEntity) {

        return "  " + diffEntity.getKey() + ": " + diffEntity.getValue1();
    }

    @Override
    public String wrap(List<String> differences) {
        String indentation = "  ";
        var content = Arrays.stream(
                                    differences.stream().collect(Collectors.joining(delimiter)).split(delimiter)
                            )
                            .collect(Collectors.joining(delimiter + indentation));
        return "{" + delimiter + indentation + content + delimiter + "}";
    }
}
