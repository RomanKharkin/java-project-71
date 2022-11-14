package hexlet.code.formatters;

import hexlet.code.differ.DiffEntity;
import hexlet.code.exceptions.FormatFailedException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FormatProcessor {
    public static String process(List<DiffEntity> differences, Formatter formatter) throws FormatFailedException {
        var content = differences
                .stream()
                .map(diffEntity -> diffEntity.format(formatter))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return formatter.wrap(content);
    }

}
