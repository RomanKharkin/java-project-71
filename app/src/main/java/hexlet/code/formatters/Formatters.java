package hexlet.code.formatters;

import hexlet.code.exceptions.FormatFailedException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Formatters {
    private static final Map<String, Formatter> formatters = Arrays
            .stream(new Formatter[]{
                    new PlainFormatter(),
                    new StylishFormatter(),
                    new JsonFormatter()
            })
            .collect(Collectors.toMap((formatter) -> formatter.getName(), Function.identity()));

    public static Formatter getFormatterByName(String formatterName) throws FormatFailedException {

        return Optional
                .ofNullable(formatters.get(formatterName))
                .orElseThrow(() -> new FormatFailedException("Нет такого формата: " + formatterName));
    }
}

