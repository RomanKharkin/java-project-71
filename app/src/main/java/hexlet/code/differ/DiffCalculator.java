package hexlet.code.differ;

import hexlet.code.Operation;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffCalculator {

    public static Map<String, DiffEntity> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        return Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                       .distinct()
                       .collect(Collectors.toMap(Function.identity(), (key) -> {
                           Operation operation = getOperation(key, map1, map2);

                           return new DiffEntity(map1.get(key), map2.get(key), operation);
                       }));
    }

    private static Operation getOperation(String key, Map<String, Object> map1, Map<String, Object> map2) {
        if (!map1.containsKey(key)) {
            return Operation.ADD;
        }

        if (!map2.containsKey(key)) {
            return Operation.REMOVE;
        }

        if (!Objects.equals(map1.get(key), map2.get(key))) {
            return Operation.REPLACE;
        }

        return Operation.UNCHAHGED;
    }
}
