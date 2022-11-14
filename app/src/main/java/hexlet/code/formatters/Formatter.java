package hexlet.code.formatters;

import hexlet.code.differ.DiffEntity;
import hexlet.code.exceptions.FormatFailedException;

import java.util.List;

public interface Formatter<T extends Object> {
    String getName();

    T add(DiffEntity diffEntity);

    T remove(DiffEntity diffEntity);

    T replace(DiffEntity diffEntity);

    String wrap(List<T> differences) throws FormatFailedException;

    default T nothing(DiffEntity diffEntity) {
        return null;
    }
}
