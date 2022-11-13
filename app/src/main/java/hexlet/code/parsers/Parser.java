package hexlet.code.parsers;

import hexlet.code.exceptions.ParsingFailedException;

import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String content) throws ParsingFailedException;
}
