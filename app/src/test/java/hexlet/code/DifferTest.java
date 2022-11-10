package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

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


    private String expectedPlainPatch = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    private String expectedJsonPatch = "[{\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"false\",\n"
            + "  \"path\" : \"chars2\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"true\",\n"
            + "  \"path\" : \"checked\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"[value1, value2]\",\n"
            + "  \"path\" : \"default\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"null\",\n"
            + "  \"path\" : \"id\"\n"
            + "}, {\n"
            + "  \"op\" : \"remove\",\n"
            + "  \"path\" : \"key1\"\n"
            + "}, {\n"
            + "  \"op\" : \"add\",\n"
            + "  \"value\" : \"value2\",\n"
            + "  \"path\" : \"key2\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"[22, 33, 44, 55]\",\n"
            + "  \"path\" : \"numbers2\"\n"
            + "}, {\n"
            + "  \"op\" : \"remove\",\n"
            + "  \"path\" : \"numbers3\"\n"
            + "}, {\n"
            + "  \"op\" : \"add\",\n"
            + "  \"value\" : \"[4, 5, 6]\",\n"
            + "  \"path\" : \"numbers4\"\n"
            + "}, {\n"
            + "  \"op\" : \"add\",\n"
            + "  \"value\" : \"{nestedKey=value, isNested=true}\",\n"
            + "  \"path\" : \"obj1\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"Another value\",\n"
            + "  \"path\" : \"setting1\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"300\",\n"
            + "  \"path\" : \"setting2\"\n"
            + "}, {\n"
            + "  \"op\" : \"replace\",\n"
            + "  \"value\" : \"none\",\n"
            + "  \"path\" : \"setting3\"\n"
            + "}]";

    private String path1;
    private String path2;

    @BeforeEach
    void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();

        String resourceName1 = "fileY1.yml";
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        path1 = file1.getPath();

        String resourceName2 = "fileY2.yml";
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        path2 = file2.getPath();
    }

    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateStylishPatch() {
        try {
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
            var actualPatch = Differ.generate(path1, path2, "plain");
            Assertions.assertEquals(expectedPlainPatch, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate plain")
    void shouldCorrectGenerateJsonPatch() {
        try {
            var actualPatch = Differ.generate(path1, path2, "json");
            Assertions.assertEquals(expectedJsonPatch, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate должен бросить исключение если файла нет")
    void shouldThrowExceptionFileNotExist() {
        try {
            Differ.generate("filetNotExisted.json", path2);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
