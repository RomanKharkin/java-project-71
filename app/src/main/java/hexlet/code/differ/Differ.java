package hexlet.code.differ;


import hexlet.code.exceptions.FileReadingException;
import hexlet.code.exceptions.FormatFailedException;
import hexlet.code.exceptions.ParsingFailedException;
import hexlet.code.formatters.FormatProcessor;
import hexlet.code.formatters.Formatters;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.YmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    private static final NullObject NULL_OBJECT = new NullObject();

    public static String generate(String filePath1, String filePath2) throws
            ParsingFailedException,
            FileReadingException,
            FormatFailedException {

        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatterName) throws
            ParsingFailedException,
            FileReadingException,
            FormatFailedException {

        var differences = getDifferences(filePath1, filePath2);

        var formatter = Formatters.getFormatterByName(formatterName);

        return FormatProcessor.process(differences, formatter);
    }

    private static Map<String, Object> convertFileToMap(String fileName) throws
            FileReadingException,
            ParsingFailedException {

        Parser parser = new YmlParser();
        String fileContent1 = getFileContent(fileName);
        return parser.parse(fileContent1);
    }

    private static String getFileContent(String fileName) throws FileReadingException {
        var path = Path.of(fileName);

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new FileReadingException("Failed to read from file: " + fileName, e);
        }
    }

    private static List<DiffEntity> getDifferences(String filePath1, String filePath2) throws
            ParsingFailedException,
            FileReadingException {
        var map1 = convertFileToMap(filePath1);
        var map2 = convertFileToMap(filePath2);
        return Stream
                .concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .map(key -> new DiffEntity(
                        key,
                        forceNullToNullObject(map1, key),
                        forceNullToNullObject(map2, key)
                ))
                .collect(Collectors.toList());
    }

    private static <Key> Object forceNullToNullObject(Map<Key, Object> map, Key key) {
        return !map.containsKey(key) ? null : Optional.ofNullable(map.get(key)).orElse(NULL_OBJECT);
    }

    private static class NullObject {
        @Override
        public String toString() {
            return "null";
        }
    }
}
