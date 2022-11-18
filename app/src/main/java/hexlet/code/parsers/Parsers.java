package hexlet.code.parsers;

public class Parsers {
    public static Parser getParserByFormat(String format) {
        switch (format) {
            case "json":
                return new JsonParser();
            case "yml":
                return new YmlParser();
            default:
                throw new Error("Нет такого формата: " + format);
        }
    }
}
