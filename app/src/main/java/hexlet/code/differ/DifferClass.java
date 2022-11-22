package hexlet.code.differ;

import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import hexlet.code.parsers.Parsers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferClass {

    public static Map<String, DiffClass> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        return Stream
                .concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .collect(Collectors.toMap(Function.identity(), (key) -> new DiffClass(
                        getNullWrapped(map1, key),
                        getNullWrapped(map2, key))));
    }

    public static Map<String, Object> getData(String fileName) throws Exception {
        var format = getDataFormat(fileName);
        var parser = Parsers.getParserByFormat(format);
        var fileContent = getFileContent(fileName);
        return parser.parse(fileContent);
    }

    private static Object getNullWrapped(Map<String, Object> map, String key) {
        if (!map.containsKey(key)) {
            return MissingNode.getInstance();
        }
        if (map.get(key) == null) {
            return NullNode.getInstance();
        }
        return map.get(key);
    }

    private static String getDataFormat(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }

        return null;
    }

    private static String getFileContent(String fileName) throws Exception {
        var path = Path.of(fileName);
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String content = Files.readString(path);

        return content;
    }
}
