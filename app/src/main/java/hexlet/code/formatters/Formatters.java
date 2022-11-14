package hexlet.code.formatters;

public class Formatters {

    public static Formatter formatterFactory(String formatterName) throws Exception {
        switch (formatterName) {
            case "plain":
                return new PlainFormatter();
            case "stylish":
                return new StylishFormatter();
            case "json":
                return new JsonFormatter();
            default:
                throw new Exception("Нет такого формата: " + formatterName);
        }
    }
}
