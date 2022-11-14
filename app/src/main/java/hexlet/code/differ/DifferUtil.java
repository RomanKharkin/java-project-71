package hexlet.code.differ;

import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.YmlParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferUtil {
    private static final DifferUtil.NullObject NULL_OBJECT = new NullObject();

    public static Map<String, DiffClass> getDifferences(String filePath1, String filePath2) throws Exception {
        var map1 = convertFileToMap(filePath1);
        var map2 = convertFileToMap(filePath2);
        return Stream
                .concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .collect(Collectors.toMap(Function.identity(), (key) -> new DiffClass(
                        forceNullToNullObject(map1, key),
                        forceNullToNullObject(map2, key))));
    }

    private static Object forceNullToNullObject(Map<String, Object> map, String key) {
        return !map.containsKey(key) ? null : Optional.ofNullable(map.get(key)).orElse(NULL_OBJECT);
    }

    static Map<String, Object> convertFileToMap(String fileName) throws Exception {
        return getParserByExtension(getFIleExtension(fileName)).parse(getFileContent(Path.of(fileName)));
    }

    static Parser getParserByExtension(String ext) {
        switch (ext) {
            case "json":
                return new JsonParser();
            case "yml":
            default:
                return new YmlParser();
        }
    }

    static String getFIleExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }

        return null;
    }

    private static String getFileContent(Path path) throws Exception {

        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл
        String content = Files.readString(path);

        return content;
    }

    private static class NullObject {
        @Override
        public String toString() {
            return "null";
        }
    }
}
