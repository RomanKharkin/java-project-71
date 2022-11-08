package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

@DisplayName("Тестируем Differ")
class DifferTest {

    private String expectedStylishPatch =
            "{\n" + "    chars1: [a, b, c]\n" + "  - chars2: [d, e, f]\n" + "  + chars2: false\n"
                    + "  - checked: false\n" + "  + checked: true\n" + "  - default: null\n"
                    + "  + default: [value1, value2]\n" + "  - id: 45\n" + "  + id: null\n" + "  - key1: value1\n"
                    + "  + key2: value2\n" + "    numbers1: [1, 2, 3, 4]\n" + "  - numbers2: [2, 3, 4, 5]\n"
                    + "  + numbers2: [22, 33, 44, 55]\n" + "  - numbers3: [3, 4, 5]\n" + "  + numbers4: [4, 5, 6]\n"
                    + "  + obj1: {nestedKey=value, isNested=true}\n" + "  - setting1: Some value\n"
                    + "  + setting1: Another value\n" + "  - setting2: 200\n" + "  + setting2: 300\n"
                    + "  - setting3: true\n" + "  + setting3: none\n" + "}";


    private String expectedPlainPatch = "Property 'chars1' was not changed: [a, b, c]\n"
            + "Property 'chars2' was updated. From [d, e, f] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [value1, value2]\n"
            + "Property 'id' was updated. From 45 to null\n" + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: value2\n" + "Property 'numbers1' was not changed: [1, 2, 3, 4]\n"
            + "Property 'numbers2' was updated. From [2, 3, 4, 5] to [22, 33, 44, 55]\n"
            + "Property 'numbers3' was removed\n" + "Property 'numbers4' was added with value: [4, 5, 6]\n"
            + "Property 'obj1' was added with value: {nestedKey=value, isNested=true}\n"
            + "Property 'setting1' was updated. From Some value to Another value\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to none";

    private Path path1;
    private Path path2;

    @BeforeEach
    void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();

        String resourceName1 = "fileY1.yml";
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        path1 = Path.of(file1.getPath());

        String resourceName2 = "fileY2.yml";
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        path2 = Path.of(file2.getPath());
    }

    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateStylishPatch() {
        try {
            Differ.setFormatter(new StylishFormatter());
            var actualPatch = Differ.generate(path1, path2);
            Assertions.assertEquals(expectedStylishPatch, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate plain")
    void shouldCorrectGeneratePlainPatch() {
        try {
            Differ.setFormatter(new PlainFormatter());
            var actualPatch = Differ.generate(path1, path2);
            Assertions.assertEquals(expectedPlainPatch, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate должен бросить исключение если файла нет")
    void shouldThrowExceptionFileNotExist() {
        try {
            Differ.generate(Path.of("filetNotExisted.json"), path2);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
