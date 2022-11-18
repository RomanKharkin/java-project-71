package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@DisplayName("Тестируем Differ")
class DifferTest {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static String pathYml1;
    private static String pathYml2;
    private static String pathJson1;
    private static String pathJson2;


    private static Path getResourcesPath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFile(String fileName) throws Exception {
        Path filePath = getResourcesPath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFile("result_json.json");
        resultPlain = readFile("result_plain.txt");
        resultStylish = readFile("result_stylish.txt");

        pathYml1 = getResourcesPath("file1.yml").toString();
        pathYml2 = getResourcesPath("file2.yml").toString();
        pathJson1 = getResourcesPath("file1.json").toString();
        pathJson2 = getResourcesPath("file2.json").toString();


    }


    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateDefaultPatchYml() {
        try {
            var actualPatch = Differ.generate(pathYml1, pathYml2);
            Assertions.assertEquals(resultStylish, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateStylishPatchYml() {
        try {
            var actualPatch = Differ.generate(pathYml1, pathYml2, "stylish");
            Assertions.assertEquals(resultStylish, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate plain")
    void shouldCorrectGeneratePlainPatchYml() {
        try {
            var actualPatch = Differ.generate(pathYml1, pathYml2, "plain");
            Assertions.assertEquals(resultPlain, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate json")
    void shouldCorrectGenerateJsonPatchYml() {
        try {
            var actualPatch = Differ.generate(pathYml1, pathYml2, "json");
            Assertions.assertEquals(resultJson, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateDefaultPatchJson() {
        try {
            var actualPatch = Differ.generate(pathJson1, pathJson2);
            Assertions.assertEquals(resultStylish, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate stylish")
    void shouldCorrectGenerateStylishPatchJson() {
        try {
            var actualPatch = Differ.generate(pathJson1, pathJson2, "stylish");
            Assertions.assertEquals(resultStylish, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate plain")
    void shouldCorrectGeneratePlainPatchJson() {
        try {
            var actualPatch = Differ.generate(pathJson1, pathJson2, "plain");
            Assertions.assertEquals(resultPlain, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate json")
    void shouldCorrectGenerateJsonPatchJson() {
        try {
            var actualPatch = Differ.generate(pathJson1, pathJson2, "json");
            Assertions.assertEquals(resultJson, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate должен бросить исключение если файла нет")
    void shouldThrowExceptionFileNotExist() {
        try {
            Differ.generate("filetNotExisted.json", pathJson2);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
